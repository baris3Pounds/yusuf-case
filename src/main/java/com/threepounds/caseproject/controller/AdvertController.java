package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.mapper.AdvertMapper;
import com.threepounds.caseproject.controller.resource.AdvertResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.*;
import com.threepounds.caseproject.data.entity.adress.City;
import com.threepounds.caseproject.data.entity.adress.County;
import com.threepounds.caseproject.data.entity.adress.Street;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

//import com.threepounds.caseproject.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/advert")
public class AdvertController {
    private final AdvertService advertService;
    private final AdvertMapper advertMapper;

    private final CategoryService categoryService;

    private final AdvertTagService advertTagService;
    //private final TagService tagService;
    private final CityService cityService;
    private final CountyService countyService;
    private final StreetService streetService;







    public AdvertController(AdvertService advertService, AdvertMapper advertMapper,
                            CategoryService categoryService, AdvertTagService advertTagService, CityService cityService, CountyService countyService, StreetService streetService) {
        this.advertService = advertService;
        this.advertMapper = advertMapper;
        this.categoryService = categoryService;
        this.advertTagService = advertTagService;
        //this.tagService = tagService;
        this.cityService = cityService;
        this.countyService = countyService;
        this.streetService = streetService;
    }
    @PostMapping("")
    public ResponseModel<AdvertResource> createAdvert(@RequestBody AdvertDto advertDto){
        Advert advertToSave= advertMapper.advertDtoToEntity(advertDto);
        advertService.save(advertToSave);
        City city = cityService.getById(advertDto.getCityId())
                .orElseThrow(() -> new NotFoundException("City Not Found"));
        County county = countyService.getById(advertDto.getCountyId())
                .orElseThrow(() -> new NotFoundException("County Not Found"));
        Street street = streetService.getById(advertDto.getStreetId())
                .orElseThrow(() -> new NotFoundException("Street Not Found"));
//        county.getStreets().add(street);
//        city.getCounties().add(county);
//        //System.out.println(countyService.getById(advertDto.getCountyId()).stream().map(s -> s.getName()));
//        cityService.save(city);
//        countyService.save(county);
//        streetService.save(street);
        advertToSave.setCityId(advertDto.getCityId());
        advertToSave.setCountyId(advertDto.getCountyId());
        advertToSave.setStreetId(advertDto.getStreetId());
        List<Tag> tags = new ArrayList<>();
        List<ESTag> esTags=new ArrayList<>();
        advertDto.getTags().forEach(t->{
            Tag tag = new Tag();
            tag.setTag(t);
            tag.setAdvert(advertToSave);
            tags.add(tag);
            advertTagService.save(tag);
           ESTag esTag=new ESTag();
           esTag.setAdvert(advertToSave);
           esTag.setTag(t);
           esTags.add(esTag);
           //tagService.save(esTag);

        });

        try {
            advertToSave.setTag(tags);
            Category category = categoryService.getById(advertDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException());
            Advert savedAdvert = advertService.save(advertToSave);
            savedAdvert.setCategory(category);
            advertService.save(savedAdvert);
            AdvertResource advertResource = advertMapper.entityToAdvertResource(savedAdvert);
            advertResource.setTags(savedAdvert.getTag().stream().map(Tag::getTag).collect(Collectors.toList()));
            return new ResponseModel<>(HttpStatus.OK.value(), advertResource, null);
        }
        catch (Exception e){
            log.error("Error",e);
        }
        return new ResponseModel<>(HttpStatus.OK.value(), null, null);
    }
    @DeleteMapping("{id}")
    @CacheEvict(value = "advert",key = "#id")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        advertService.delete(id);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/{id}")
    //@Cacheable(value = "advert",key = "#id")
    public ResponseModel<AdvertResource> getOneAdvert(@PathVariable UUID id){
        Advert advert= advertService.getById(id)
                .orElseThrow(()-> new IllegalArgumentException() );
        if(advert.getCounter()==null){
            advert.setCounter(0);
        }
        advert.setCounter(advert.getCounter()+1);
        advertService.save(advert);
        System.out.println(advert.getCounter());
        AdvertResource advertResource = advertMapper.entityToAdvertResource(advert);
        return new ResponseModel<>(HttpStatus.OK.value(),advertResource,null);
    }
    @PutMapping("/{id}")
    @CachePut(value = "advert",key="#id")
    public ResponseModel<AdvertResource> update(@PathVariable UUID id,@RequestBody AdvertDto advertDto){
        Advert existingAdvert=advertService.getById(id)
                .orElseThrow(()->new RuntimeException());
        existingAdvert.setCityId(advertDto.getCityId());
        existingAdvert.setCountyId(advertDto.getCountyId());
        existingAdvert.setStreetId(advertDto.getStreetId());


        Advert mappedAdvert=advertMapper.advertDtoToEntity(advertDto);
        mappedAdvert.setId(existingAdvert.getId());

        Advert updateAdvert=advertService.save(mappedAdvert);
        AdvertResource advertResource= advertMapper.entityToAdvertResource(updateAdvert);
        return new ResponseModel<>(HttpStatus.OK.value(),advertResource,null);

    }
    @GetMapping("")
    public ResponseEntity<List<AdvertResource>> list(){
        List<AdvertResource> advertResources=advertMapper.entityToAdvertResource(advertService.getAllAdvert());
        return ResponseEntity.ok(advertResources);
    }

    @GetMapping("/category/{id}")
    public ResponseModel getCategoryWithAdvert(@PathVariable UUID id) {
        Category category = categoryService.getById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        List<Advert> adverts = advertService.advertsByCategory(category);
        List<AdvertResource> advertResources = advertMapper.entityToAdvertResource(adverts);
        return new ResponseModel<>(HttpStatus.OK.value(),advertResources,null);

    }
    @GetMapping("/page")
    public ResponseModel<List<AdvertResource>> listByPage(@RequestParam int pageNumber, @RequestParam int pageSize) {

        Page<Advert> adverts = advertService.listByPage(pageNumber, pageSize);

        List<AdvertResource> advertResources = advertMapper.entityToAdvertResource(
                adverts.toList());

        return new ResponseModel<>(HttpStatus.OK.value(), advertResources, null,
                (int) adverts.getTotalElements(), adverts.getTotalPages());
    }
    /*
    @GetMapping("/es")
    public ResponseModel<Iterable<ESTag>> getAll(){
        Iterable<ESTag> esTags=tagService.getAllTags();
        return new ResponseModel<>(HttpStatus.OK.value(),esTags,null);
    }
    @PostMapping("/es/search")
    public  ResponseModel<List<ESTag>> searchAdvertWithTag(@RequestBody EsDto esDto){
              List<ESTag>esTags= tagService.searchAdvert(esDto);
        return new ResponseModel<>(HttpStatus.OK.value(),esTags,null);
    }
*/

    // TODO , put mapping endpoint , ilanın gösterilme sayısını artırsın
    @PutMapping("/display/{id}")
    @CachePut(value = "advert",key="#id")
    public Integer displayCount(@PathVariable UUID id){
        Advert existingAdvert=advertService.getById(id)
                .orElseThrow(()->new RuntimeException());
        if(existingAdvert.getCounter()==null){
            existingAdvert.setCounter(0);
        }
        System.out.println(existingAdvert.getCounter());

        return existingAdvert.getCounter();

    }


}