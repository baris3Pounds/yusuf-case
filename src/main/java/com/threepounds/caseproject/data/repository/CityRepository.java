package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.adress.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City,UUID> {

    List<City> findById(City city);

}
