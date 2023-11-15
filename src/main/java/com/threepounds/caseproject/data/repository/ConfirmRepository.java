package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.data.entity.ValidationCode;
import com.threepounds.caseproject.security.auth.ConfirmRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmRepository extends JpaRepository<ValidationCode, UUID> {
    Optional<ValidationCode> findById(UUID user_id);

}
