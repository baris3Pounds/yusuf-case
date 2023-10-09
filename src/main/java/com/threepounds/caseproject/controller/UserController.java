package com.threepounds.caseproject.controller;
import com.threepounds.caseproject.controller.dto.UserDto;
import com.threepounds.caseproject.controller.mapper.UserMapper;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<User>  createUser(@RequestBody UserDto userDto){
        User userToSave=userMapper.userDtoToEntity(userDto);
        User savedUser=userService.saveUser(userToSave);
        return ResponseEntity.ok(savedUser);
    }
    @GetMapping("{userId}")
    public ResponseEntity getOneUser(@PathVariable UUID userId){
        userService.getByUserId(userId)
                .orElseThrow(()->new NotFoundException("User not found"));

      return ResponseEntity.ok(userService.getUser(userId));
    }
    @DeleteMapping("{userId}")
    public ResponseEntity deleteOneUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("Success");
    }
    @PutMapping("{userId}")
            public ResponseEntity updateOneUser(@PathVariable UUID userId,@RequestBody UserDto userDto){
        User existingUser=userService.getByUserIdToUpdate(userId)
                .orElseThrow(()->new NotFoundException("User not found"));
        User mappedUser=userMapper.userDtoToEntity(userDto);
        mappedUser.setUserID(existingUser.getUserID());
        User updateUser=userService.saveUser(mappedUser);
        return ResponseEntity.ok(updateUser);
    }


}
