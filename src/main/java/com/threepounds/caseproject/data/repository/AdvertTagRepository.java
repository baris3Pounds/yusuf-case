package com.threepounds.caseproject.data.repository;
import com.threepounds.caseproject.data.entity.AdvertTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AdvertTagRepository extends JpaRepository<AdvertTag, UUID> {

}
