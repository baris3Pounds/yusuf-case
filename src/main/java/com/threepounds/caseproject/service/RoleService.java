package com.threepounds.caseproject.service;

import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.data.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role save(Role role){
        return roleRepository.save(role);
    }
    public void delete(UUID id){
        roleRepository.deleteById(id);
    }
    public Optional <Role> getById(UUID id){
        return roleRepository.findById(id);
    }
    public List <Role> getRoles(){
        return roleRepository.findAll();
    }
}
