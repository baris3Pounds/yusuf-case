package com.threepounds.caseproject.service;
import com.threepounds.caseproject.data.entity.Tag;
import com.threepounds.caseproject.data.repository.TagRepository;
import org.springframework.stereotype.Service;
@Service
public class TagService {
    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public Tag save(Tag tag){

       return tagRepository.save(tag);

    }
}
