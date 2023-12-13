package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.adress.City;
import com.threepounds.caseproject.data.entity.adress.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CountyRepository extends JpaRepository<County,UUID> {

    List<County> findById(County county);

}
