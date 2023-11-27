package com.threepounds.caseproject.service;

import com.threepounds.caseproject.data.entity.AdvertTag;
import com.threepounds.caseproject.data.repository.AdvertTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertTagService {

    private final AdvertTagRepository advertTagRepository;

    public AdvertTagService(AdvertTagRepository advertTagRepository) {
        this.advertTagRepository = advertTagRepository;
    }

    public List<AdvertTag> getAllAdvert(){
        return advertTagRepository.findAll();
    }



}
