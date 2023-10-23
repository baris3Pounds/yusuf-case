package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.FeaturesDto;
import com.threepounds.caseproject.controller.mapper.FeaturesMapper;
import com.threepounds.caseproject.controller.resource.FeaturesResource;
import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.data.entity.Features;
import com.threepounds.caseproject.service.CategoryService;
import com.threepounds.caseproject.service.FeaturesService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/features")
public class FeaturesController {
    private final FeaturesService featuresService;
    private final FeaturesMapper featuresMapper;
    private final CategoryService categoryService;

    public FeaturesController(FeaturesService featuresService, FeaturesMapper featuresMapper, CategoryService categoryService) {
        this.featuresService = featuresService;
        this.featuresMapper = featuresMapper;
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<FeaturesResource> create(@RequestBody FeaturesDto featuresDto){
        Features featuresToSave=featuresMapper.dtoToEntity(featuresDto);
        Category category= categoryService.getById(featuresDto.getCategoryId())
                .orElseThrow(()->new IllegalArgumentException());
        featuresToSave.setCategory(category);
        Features savedFeature =featuresService.save(featuresToSave);
        FeaturesResource featuresResource = featuresMapper.featureToResource(savedFeature);
        return ResponseEntity.ok(featuresResource);

    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        featuresService.delete(id);
        return ResponseEntity.ok("Ok");
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable UUID id,@RequestBody FeaturesDto featuresDto){
        Features existingFeature=featuresService.getById(id)
                .orElseThrow(()-> new RuntimeException());
        Features mappedFeature=featuresMapper.dtoToEntity(featuresDto);
        mappedFeature.setId(existingFeature.getId());
        Features updateFeature=featuresService.save(mappedFeature);
        FeaturesResource featuresResource=featuresMapper.featureToResource(updateFeature);
        return ResponseEntity.ok(featuresResource);

    }
    @GetMapping("{id}")
    public ResponseEntity getOneFeature(@PathVariable UUID id){
        Features features=featuresService.getById(id)
                .orElseThrow(()->new RuntimeException());
        FeaturesResource featuresResource=featuresMapper.featureToResource(features);
        return ResponseEntity.ok(featuresResource);
    }
    @GetMapping("")
    public ResponseEntity<List<FeaturesResource>> list(){
        List<FeaturesResource> featuresResources = featuresMapper.featuresToResourceList(
            featuresService.getAllFeatures());
        return ResponseEntity.ok(featuresResources);
    }



}
