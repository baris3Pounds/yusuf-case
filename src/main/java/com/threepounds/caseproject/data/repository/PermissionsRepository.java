package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.Permission;
import com.threepounds.caseproject.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionsRepository extends JpaRepository<Permission, UUID> {
    Optional<Permission> findByName(String name);
}
