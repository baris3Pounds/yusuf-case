package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.AdvertTag;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableElasticsearchRepositories
public interface AdvertTagRepository extends ElasticsearchRepository<AdvertTag, UUID> {
}
