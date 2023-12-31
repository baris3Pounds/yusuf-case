package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.dto.EsDto;
import com.threepounds.caseproject.controller.mapper.AdvertMapper;
import com.threepounds.caseproject.controller.resource.AdvertResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.ESTag;
import com.threepounds.caseproject.data.entity.Tag;
import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.AdvertService;
import com.threepounds.caseproject.service.AdvertTagService;
import com.threepounds.caseproject.service.CategoryService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

import com.threepounds.caseproject.service.TagService;
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
    private final TagService tagService;



    public AdvertController(AdvertService advertService, AdvertMapper advertMapper,
                            CategoryService categoryService, AdvertTagService advertTagService, TagService tagService) {
        this.advertService = advertService;
        this.advertMapper = advertMapper;
        this.categoryService = categoryService;
        this.advertTagService = advertTagService;
        this.tagService = tagService;
    }
    @PostMapping("")
    public ResponseModel<AdvertResource> createAdvert(@RequestBody AdvertDto advertDto){
        Advert advertToSave= advertMapper.advertDtoToEntity(advertDto);
        advertService.save(advertToSave);
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
           tagService.save(esTag);

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


}