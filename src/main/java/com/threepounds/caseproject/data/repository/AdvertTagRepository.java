package com.threepounds.caseproject.data.repository;
import com.threepounds.caseproject.data.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface AdvertTagRepository extends JpaRepository<Tag,UUID> {
}