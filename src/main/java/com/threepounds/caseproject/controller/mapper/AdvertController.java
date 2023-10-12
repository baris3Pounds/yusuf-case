package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.resource.AdvertResource;
import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.exceptions.RestResponseEntityExceptionHandler;
import com.threepounds.caseproject.service.AdvertService;
import com.threepounds.caseproject.service.CategoryService;
import java.util.List;
import java.util.UUID;
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
@RequestMapping("/advert")
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
    public ResponseEntity<Advert> createAdvert(@RequestBody AdvertDto advertDto){
        Advert advertToSave= advertMapper.advertDtoToEntity(advertDto);
        Category category = categoryService.getById(advertDto.getCategoryId())
            .orElseThrow(()-> new IllegalArgumentException());
        advertToSave.setCategory(category);
        Advert savedAdvert=advertService.save(advertToSave);

        return ResponseEntity.ok(savedAdvert);

    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id){
         advertService.delete(id);
        return ResponseEntity.ok("success");
    }
    @GetMapping("{id}")
    public ResponseEntity getOneAdvert(@PathVariable UUID id){
       Advert advert= advertService.getById(id)
                .orElseThrow(()-> new IllegalArgumentException() );
        AdvertResource advertResource = advertMapper.entityToAdvertResource(advert);
        return ResponseEntity.ok(advertResource);
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable UUID id,@RequestBody AdvertDto advertDto){
        Advert existingAdvert=advertService.getById(id)
                .orElseThrow(()->new NotFoundException("Advert not found"));
        Advert mappedAdvert=advertMapper.advertDtoToEntity(advertDto);
        mappedAdvert.setId(existingAdvert.getId());
        Advert updateAdvert=advertService.save(mappedAdvert);
        return ResponseEntity.ok(updateAdvert);
    }
    @GetMapping("")
    public ResponseEntity<List<Advert>> list(){
        return ResponseEntity.ok(advertService.getAllAdvert());
    }


 }
