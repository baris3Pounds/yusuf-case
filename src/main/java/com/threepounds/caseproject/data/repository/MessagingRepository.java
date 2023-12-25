package com.threepounds.caseproject.data.repository;

import com.threepounds.caseproject.data.entity.Messaging;
import com.threepounds.caseproject.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessagingRepository extends JpaRepository<Messaging, UUID> {

}
