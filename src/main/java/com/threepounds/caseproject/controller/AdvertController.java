package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.mapper.AdvertMapper;
import com.threepounds.caseproject.controller.resource.AdvertResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.AdvertTag;
import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.data.entity.Tag;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.AdvertService;
import com.threepounds.caseproject.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.threepounds.caseproject.service.TagService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/advert")
public class AdvertController {
    private final AdvertService advertService;
    private final AdvertMapper advertMapper;
    private final TagService tagService;
    private final CategoryService categoryService;



    public AdvertController(AdvertService advertService, AdvertMapper advertMapper,
                            TagService tagService, CategoryService categoryService) {
        this.advertService = advertService;
        this.advertMapper = advertMapper;
        this.tagService = tagService;

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
        Tag tag=new Tag();
        tag.setAdvert(savedAdvert);
        tagService.save(tag);
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
    @GetMapping("/page")
    public ResponseModel<List<AdvertResource>> listByPage(@RequestParam int pageNumber, @RequestParam int pageSize) {

        Page<Advert> adverts = advertService.listByPage(pageNumber, pageSize);

        List<AdvertResource> advertResources = advertMapper.entityToAdvertResource(
                adverts.toList());

        return new ResponseModel<>(HttpStatus.OK.value(), advertResources, null,
                (int) adverts.getTotalElements(), adverts.getTotalPages());
    }


 }
