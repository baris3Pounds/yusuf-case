package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.AdvertTag;
import com.threepounds.caseproject.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvertTagRepository extends JpaRepository<AdvertTag,UUID> {

}
