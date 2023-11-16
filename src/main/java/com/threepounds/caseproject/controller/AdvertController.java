package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.mapper.AdvertMapper;
import com.threepounds.caseproject.controller.resource.AdvertResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.AdvertService;
import com.threepounds.caseproject.service.CategoryService;
import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/advert")
public class AdvertController {
    private final AdvertService advertService;
    private final AdvertMapper advertMapper;

    private final CategoryService categoryService;



    public AdvertController(AdvertService advertService, AdvertMapper advertMapper,
        CategoryService categoryService) {
        this.advertService = advertService;
        this.advertMapper = advertMapper;

        this.categoryService = categoryService;
    }
    @PostMapping("")
    public ResponseModel<AdvertResource> createAdvert(@RequestBody AdvertDto advertDto){
        Advert advertToSave= advertMapper.advertDtoToEntity(advertDto);
        Category category = categoryService.getById(advertDto.getCategoryId())
            .orElseThrow(()-> new IllegalArgumentException());
        Advert savedAdvert=advertService.save(advertToSave);
        savedAdvert.setCategory(category);
        advertService.save(savedAdvert);
        AdvertResource advertResource=advertMapper.entityToAdvertResource(savedAdvert);
        return new ResponseModel<>(HttpStatus.OK.value(),advertResource, null);

    }
    @DeleteMapping("{id}")
    @CacheEvict(value = "advert",key = "#id")
    public ResponseEntity<String> delete(@PathVariable UUID id){
         advertService.delete(id);
        return ResponseEntity.ok("success");
    }
    @GetMapping("{id}")
    @Cacheable(value = "advert",key = "#id")
    public ResponseModel<AdvertResource> getOneAdvert(@PathVariable UUID id){
       Advert advert= advertService.getById(id)
                .orElseThrow(()-> new IllegalArgumentException() );
        AdvertResource advertResource = advertMapper.entityToAdvertResource(advert);
        return new ResponseModel<>(HttpStatus.OK.value(),advertResource,null);
    }
    @PutMapping("{id}")
    @CachePut(value = "advert",key="#id")
    public ResponseModel<AdvertResource> update(@PathVariable UUID id,@RequestBody AdvertDto advertDto){
        Advert existingAdvert=advertService.getById(id)
                .orElseThrow(()->new RuntimeException());
        Advert mappedAdvert=advertMapper.advertDtoToEntity(advertDto);
        mappedAdvert.setId(existingAdvert.getId());
        Advert updateAdvert=advertService.save(mappedAdvert);
        AdvertResource advertResource= advertMapper.entityToAdvertResource(updateAdvert);
        return new ResponseModel<>(HttpStatus.OK.value(),advertResource,null);

    }
    @GetMapping("")
    public ResponseEntity<List<AdvertResource>> list(){
        List<AdvertResource> advertResource=advertMapper.entityToAdvertResource(advertService.getAllAdvert());
        return ResponseEntity.ok(advertResource);
    }

    @GetMapping("/category/{id}")
    public ResponseModel getCategoryWithAdvert(@PathVariable UUID id) {
        Category category = categoryService.getById(id)
            .orElseThrow(() -> new NotFoundException("Category not found"));

        List<Advert> adverts = advertService.advertsByCategory(category);
        List<AdvertResource> advertResources = advertMapper.entityToAdvertResource(adverts);
        return new ResponseModel<>(HttpStatus.OK.value(),advertResources,null);

    }


 }
