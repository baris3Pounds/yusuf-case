package com.threepounds.caseproject.service;

import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.data.entity.Features;
import com.threepounds.caseproject.data.repository.FeaturesRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeaturesService {
private final FeaturesRepository featuresRepository;

    public FeaturesService(FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }
    public Features save(Features features){
        return featuresRepository.save(features);
    }
    public void delete(UUID id){
         featuresRepository.deleteById(id);
    }

    public Optional<Features> getById(UUID id){
        return featuresRepository.findById(id);
    }
    public List<Features> getAllFeatures(){
        return featuresRepository.findAll();
    }
    public List<Features> featuresByCategory(Category category){
        return featuresRepository.findByCategory(category);
    }
}
