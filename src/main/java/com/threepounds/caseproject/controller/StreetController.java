package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.CountyDto;
import com.threepounds.caseproject.controller.dto.StreetDto;
import com.threepounds.caseproject.controller.mapper.StreetMapper;
import com.threepounds.caseproject.controller.resource.CountyResource;
import com.threepounds.caseproject.controller.resource.StreetResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.adress.County;
import com.threepounds.caseproject.data.entity.adress.Street;
import com.threepounds.caseproject.service.StreetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/street")
public class StreetController {
    private final StreetService streetService;
    private final StreetMapper streetMapper;

    public StreetController(StreetService streetService, StreetMapper streetMapper) {
        this.streetService = streetService;
        this.streetMapper = streetMapper;
    }


    @PostMapping("")
    public ResponseModel<StreetResource> createStreet(@RequestBody StreetDto streetDto){
        Street street = streetMapper.streetDtoToEntity(streetDto);
        Street savedStreet=streetService.save(street);
        StreetResource streetResource = streetMapper.entityToStreetResource(savedStreet);

        return new ResponseModel<>(HttpStatus.OK.value(), streetResource, null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        streetService.delete(id);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/{id}")
    public ResponseModel<StreetResource> getOneStreet (@PathVariable UUID id){
        Street street= streetService.getById(id)
                .orElseThrow(()-> new IllegalArgumentException() );
        StreetResource streetResource = streetMapper.entityToStreetResource(street);
        return new ResponseModel<>(HttpStatus.OK.value(),streetResource,null);
    }

    @GetMapping("")
    public ResponseEntity<List<StreetResource>> listStreets(){
        List<StreetResource> streetResources = streetMapper.entityToStreetResource(streetService.getAllStreets());

        return ResponseEntity.ok(streetResources);
    }


}