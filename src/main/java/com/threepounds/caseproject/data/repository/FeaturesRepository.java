package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.data.entity.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeaturesRepository extends JpaRepository<Features,UUID> {
    List<Features> findByCategory(Category category);
}
