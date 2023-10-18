package com.threepounds.caseproject.controller;
import com.threepounds.caseproject.controller.dto.UserDto;
import com.threepounds.caseproject.controller.mapper.UserMapper;
import com.threepounds.caseproject.controller.resource.CategoryResource;
import com.threepounds.caseproject.controller.resource.UserResource;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserResource>  createUser(@RequestBody UserDto userDto){
        User userToSave=userMapper.userDtoToEntity(userDto);
        User savedUser=userService.saveUser(userToSave);
       UserResource userResource=userMapper.userDto(savedUser);

        return ResponseEntity.ok(userResource);
    }
    @GetMapping("{id}")
    public ResponseEntity getOneUser(@PathVariable UUID id){
        User user = userService.getByUserId(id)
            .orElseThrow(() -> new RuntimeException());
           UserResource userResource= userMapper.userDto(user);
      return ResponseEntity.ok(userResource);
    }
    @DeleteMapping("{userId}")
    public ResponseEntity deleteOneUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("Success");
    }
    @PutMapping("{userId}")
            public ResponseEntity updateOneUser(@PathVariable UUID userId,@RequestBody UserDto userDto){
        User existingUser=userService.getByUserId(userId)
                .orElseThrow(()->new RuntimeException());
        User mappedUser=userMapper.userDtoToEntity(userDto);
        mappedUser.setId(existingUser.getId());
        User updateUser=userService.saveUser(mappedUser);
        userMapper.userDto(mappedUser);
        return ResponseEntity.ok(updateUser);
    }
    @GetMapping("")
    public ResponseEntity<List<UserResource>> list(){
        List<UserResource> userResources = userMapper.userDtoToList(
                userService.list());
        return ResponseEntity.ok(userResources);
    }


}
