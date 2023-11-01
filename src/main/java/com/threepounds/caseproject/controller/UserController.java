package com.threepounds.caseproject.controller;
import com.threepounds.caseproject.controller.dto.UserDto;
import com.threepounds.caseproject.controller.mapper.UserMapper;
import com.threepounds.caseproject.controller.resource.UserResource;
import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.service.RoleService;
import com.threepounds.caseproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserMapper userMapper, UserService userService, RoleService roleService,
        PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("")
    public ResponseEntity<UserResource>  createUser(@RequestBody UserDto userDto){
        User userToSave=userMapper.userDtoToEntity(userDto);
        List<Role> roles = roleService.list(userDto.getRoles());
        userToSave.setRoles(roles);
        userToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser=userService.saveUser(userToSave);
       UserResource userResource=userMapper.userDto(savedUser);

        return ResponseEntity.ok(userResource);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserResource> getOneUser(@PathVariable UUID id){
        User user = userService.getByUserId(id)
            .orElseThrow(() -> new RuntimeException());
           UserResource userResource= userMapper.userDto(user);
      return ResponseEntity.ok(userResource);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteOneUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("Success");
    }
    @PutMapping("{userId}")
            public ResponseEntity<UserResource> updateOneUser(@PathVariable UUID userId,@RequestBody UserDto userDto){
        User existingUser=userService.getByUserId(userId)
                .orElseThrow(()->new RuntimeException());
        User mappedUser=userMapper.userDtoToEntity(userDto);
        List<Role> roles = roleService.list(userDto.getRoles());
        mappedUser.setRoles(roles);
        mappedUser.setId(existingUser.getId());
        User updateUser=userService.saveUser(mappedUser);
        UserResource userResource=userMapper.userDto(updateUser);
        return ResponseEntity.ok(userResource);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<UserResource>> list(){
        List<UserResource> userResources = userMapper.userDtoToList(
                userService.list());
        return ResponseEntity.ok(userResources);
    }


}
