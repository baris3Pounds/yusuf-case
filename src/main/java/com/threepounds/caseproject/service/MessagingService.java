package com.threepounds.caseproject.service;

import com.threepounds.caseproject.data.entity.Messaging;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.data.repository.MessagingRepository;
import com.threepounds.caseproject.data.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessagingService {
    private final MessagingRepository messagingRepository;
    private final UserRepository userRepository;

    public MessagingService(MessagingRepository messagingRepository, UserRepository userRepository) {
        this.messagingRepository = messagingRepository;
        this.userRepository = userRepository;
    }
    public Messaging save(Messaging messaging){
        return messagingRepository.save(messaging);
    }
    public void delete(UUID id){
        messagingRepository.findById(id);

    }
    public Page<Messaging> listByPage(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return messagingRepository.findAll(pageable);
    }
    public Optional<Messaging> getById(UUID id){
        return messagingRepository.findById(id);
    }
    public Optional<Messaging>  getReceiveMessage(UUID id){
        return messagingRepository.findById(id);
    }
    public Optional<User> getUserId(Principal principal){
        Optional<User> user =userRepository.findByEmail(principal.getName());


        return user;
   }

}
