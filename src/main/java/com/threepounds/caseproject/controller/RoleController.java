package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.RoleDto;
import com.threepounds.caseproject.controller.mapper.RoleMapper;
import com.threepounds.caseproject.controller.resource.RoleResource;
import com.threepounds.caseproject.data.entity.Permission;
import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.service.PermissionService;
import com.threepounds.caseproject.service.RoleService;
import com.threepounds.caseproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    private final PermissionService permissionService;

    public RoleController(RoleService roleService, RoleMapper roleMapper, UserService userService,
        PermissionService permissionService) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.permissionService = permissionService;
    }

    @PostMapping("")
    public ResponseEntity<RoleResource> create(@RequestBody RoleDto roleDto){
        Role roleToSave=roleMapper.dtoToEntity(roleDto);
        List<Permission> permissions = permissionService.list(roleDto.getPermissions());
        roleToSave.setPermissions(permissions);
        Role savedRole=roleService.save(roleToSave);
        RoleResource roleResource=roleMapper.roleDto(savedRole);
        return ResponseEntity.ok(roleResource);
    }
    @PutMapping("{id}")
    public ResponseEntity<RoleResource> update(@PathVariable UUID id,@RequestBody RoleDto roleDto){
        Role existingRole=roleService.getById(id)
                .orElseThrow(()->new RuntimeException());
        Role mappedRole=roleMapper.dtoToEntity(roleDto);
        List<Permission> permissions = permissionService.list(roleDto.getPermissions());
        mappedRole.setPermissions(permissions);
        mappedRole.setId(existingRole.getId());
        Role updateRole=roleService.save(mappedRole);
        RoleResource roleResource = roleMapper.roleDto(updateRole);
        return ResponseEntity.ok(roleResource);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        roleService.delete(id);
        return ResponseEntity.ok("success");
    }
    @GetMapping("{id}")
    public ResponseEntity<RoleResource> getOneRole(@PathVariable UUID id){
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
