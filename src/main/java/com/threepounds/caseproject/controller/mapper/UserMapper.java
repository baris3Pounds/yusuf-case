package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.UserDto;
import com.threepounds.caseproject.controller.resource.UserResource;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.security.auth.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.text.html.parser.ContentModel;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {
    @Mapping(source = "roles" , target = "roles", ignore = true)
    User userDtoToEntity(UserDto userDto);
    UserResource userDto(User user);
    @Mapping(source = "roles" , target = "roles", ignore = true)
    List<UserResource> userDtoToList(List<User> user);



    User userDtoToEntity(SignUpRequest signUpRequest);

}
