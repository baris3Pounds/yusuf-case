package com.threepounds.caseproject.service;



import com.threepounds.caseproject.data.repository.AdvertTagRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertTagService {
    private final AdvertTagRepository advertTagRepository;


    public AdvertTagService(AdvertTagRepository advertTagRepository) {
        this.advertTagRepository = advertTagRepository;
    }
}
