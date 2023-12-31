package com.threepounds.caseproject.service;
import com.threepounds.caseproject.data.entity.Advert;

import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.data.repository.AdvertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdvertService {


    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }
    public Advert save(Advert advert){

        return advertRepository.save(advert);
    }
    public void delete(UUID id){
         advertRepository.deleteById(id);

    }
    public Optional<Advert> getById(UUID id){
        return advertRepository.findById(id);
    
    }
    public List<Advert> getAllAdvert(){
        return advertRepository.findAll();
    }

    public List<Advert> advertsByCategory(Category category){
        return advertRepository.findByCategory(category);
    }
    public Page<Advert> listByPage(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return advertRepository.findAll(pageable);
    }
    
}
