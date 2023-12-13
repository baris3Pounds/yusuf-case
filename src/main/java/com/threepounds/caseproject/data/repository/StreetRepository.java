package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.adress.County;
import com.threepounds.caseproject.data.entity.adress.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StreetRepository extends JpaRepository<Street,UUID> {

    List<Street> findById(Street street);

}
