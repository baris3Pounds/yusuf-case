package com.threepounds.caseproject.data.repository;
import com.threepounds.caseproject.data.entity.Tag;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface TagRepository extends ElasticsearchRepository<Tag, String> {
    @Query("{\"bool\": {\"must\": [{\"match\": {\"advert.title\": \"siyah kol saati\"}}]}}")
    List<Tag> customQuery (String name, Pageable pageable);
}
