package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.AdvertDto;

import com.threepounds.caseproject.data.entity.Advert;

import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.AdvertService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;
    private final AdvertMapper advertMapper;



    public AdvertController(AdvertService advertService, AdvertMapper advertMapper) {
        this.advertService = advertService;
        this.advertMapper = advertMapper;

    }
    @PostMapping("")
    public ResponseEntity<Advert> createAdvert(@RequestBody AdvertDto advertDto){
        Advert advertToSave= advertMapper.advertDtoToEntity(advertDto);
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
                .orElseThrow(()-> new RuntimeException("Advert not found"));
        return ResponseEntity.ok(advert);
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
