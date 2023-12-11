package com.threepounds.caseproject.controller;

import ch.qos.logback.classic.Logger;
import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.dto.EsDto;
import com.threepounds.caseproject.controller.dto.FavouritesDto;
import com.threepounds.caseproject.controller.dto.RoleDto;
import com.threepounds.caseproject.controller.mapper.AdvertMapper;
import com.threepounds.caseproject.controller.mapper.FavouritesMapper;
import com.threepounds.caseproject.controller.resource.AdvertResource;
import com.threepounds.caseproject.controller.resource.FavouritesResource;
import com.threepounds.caseproject.controller.resource.FeaturesResource;
import com.threepounds.caseproject.controller.resource.RoleResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.*;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouritesController {
    FavouritesMapper favouritesMapper;
    FavouriteService favouriteService;

    public FavouritesController(FavouritesMapper favouritesMapper, FavouriteService favouriteService) {
        this.favouritesMapper = favouritesMapper;
        this.favouriteService = favouriteService;
    }

    @PostMapping("")
    public ResponseModel<FavouritesResource> createMessage(@RequestBody FavouritesDto favouritesDto){
        Favourites favourites= favouritesMapper.favouriteDtoToEntity(favouritesDto);
        favourites.setUser_id(favouritesDto.getUser_id());
        favourites.setAdvert_id(favouritesDto.getAdvert_id());
        favouriteService.save(favourites);
        FavouritesResource favouritesResource1=favouritesMapper.entityToFavouriteResource(favourites);

        return new ResponseModel<>(HttpStatus.OK.value(),favouritesResource1,null);
    }

    @GetMapping("")
    public ResponseEntity<List<FavouritesResource>> list(){
    List<FavouritesResource> favouritesResources=favouritesMapper.entityToFavouriteResources(favouriteService.getAllFavourites());

    return ResponseEntity.ok(favouritesResources);

}


    @DeleteMapping("/{advert_id}")
    @CacheEvict(value = "favourites",key = "#advert_id")
    public ResponseEntity<String> delete(@PathVariable UUID advert_id){
        favouriteService.remove(advert_id);
        return ResponseEntity.ok("success");
    }
/*
    @DeleteMapping("/{advert_id}")
    @CacheEvict("favourites")
    public ResponseEntity<String> delete(@RequestBody FavouritesDto favouritesDto){
        favouriteService.remove(favouritesDto.getAdvert_id());
        return ResponseEntity.ok("success");
    }
*/
    


}
