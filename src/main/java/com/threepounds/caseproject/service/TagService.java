package com.threepounds.caseproject.service;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.threepounds.caseproject.controller.dto.EsDto;
import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.ESTag;
import com.threepounds.caseproject.data.repository.ESTagRepository;
import com.threepounds.caseproject.util.ESUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagService {
    private final ESTagRepository esTagRepository;
    private final ElasticsearchClient elasticsearchClient;


    public TagService(ESTagRepository esTagRepository, ElasticsearchClient elasticsearchClient) {
        this.esTagRepository = esTagRepository;
        this.elasticsearchClient = elasticsearchClient;
    }

    public ESTag save(ESTag esTag){

       return esTagRepository.save(esTag);

    }

    public Iterable<ESTag> getAllTags() {
        return esTagRepository.findAll();
    }
    public List<ESTag> searchAdvert(EsDto esDto){
        Supplier<Query> query=ESUtil.buildQuery(esDto.getFieldName().get(0),
                esDto.getSearchValue().get(0));

      log.info("Elasticsearch Query{}",query.toString());
      SearchResponse<ESTag> response=null;
      try{
          response=elasticsearchClient.search(q->q.index("tag").query(query.get()),ESTag.class);
      }catch (IOException e){
          throw new RuntimeException(e);
      }
      log.info("Elasticsearch response{}",response);
        return  ex(response);
    }
    public List<ESTag> ex(SearchResponse<ESTag> response){
        return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }
}
