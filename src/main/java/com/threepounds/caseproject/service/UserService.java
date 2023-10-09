package com.threepounds.caseproject.service;

import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(UUID userId){
        userRepository.deleteById(userId);
    }
    public User getUser(UUID userId){

        return userRepository.getById(userId);
    }
    public Optional<User> getByUserId(UUID userId){
        return userRepository.findById(userId);
    }
    public Optional<User> getByUserIdToUpdate(UUID userId){
        return userRepository.findById(userId);
    }
}
