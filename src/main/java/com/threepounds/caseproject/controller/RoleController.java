package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.RoleDto;
import com.threepounds.caseproject.controller.mapper.RoleMapper;
import com.threepounds.caseproject.controller.resource.RoleResource;
import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.service.RoleService;
import com.threepounds.caseproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final UserService userService;

    public RoleController(RoleService roleService, RoleMapper roleMapper, UserService userService) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<RoleResource> create(@RequestBody RoleDto roleDto){
        Role roleToSave=roleMapper.dtoToEntity(roleDto);
        User user=userService.getByUserId(roleDto.getUserId())
                .orElseThrow(()->new RuntimeException());
        roleToSave.setUser(user);
        Role savedRole=roleService.save(roleToSave);
        RoleResource roleResource=roleMapper.roleDto(savedRole);
        return ResponseEntity.ok(roleResource);
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable UUID id,@RequestBody RoleDto roleDto){
        Role existingRole=roleService.getById(id)
                .orElseThrow(()->new RuntimeException());
        Role mappedRole=roleMapper.dtoToEntity(roleDto);
        mappedRole.setId(existingRole.getId());
        Role updateRole=roleService.save(mappedRole);
        roleMapper.roleDto(mappedRole);
        return ResponseEntity.ok(updateRole);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        roleService.delete(id);
        return ResponseEntity.ok("success");
    }
    @GetMapping("{id}")
    public ResponseEntity getOneRole(@PathVariable UUID id){
        Role role=roleService.getById(id)
                .orElseThrow(()->new RuntimeException());
        RoleResource roleResource=roleMapper.roleDto(role);
        return ResponseEntity.ok(roleResource);
    }
    @GetMapping("")
    public ResponseEntity<List<RoleResource>> list(){
        List<RoleResource> roleResources=roleMapper.roleDtoToList(roleService.getRoles());
        return ResponseEntity.ok(roleResources);
    }
}
