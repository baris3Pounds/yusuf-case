package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.UserDto;
import com.threepounds.caseproject.controller.resource.UserResource;
import com.threepounds.caseproject.data.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.text.html.parser.ContentModel;
import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {
    User userDtoToEntity(UserDto userDto);
    UserResource userDto(User user);
    @Mapping(source = "role" , target = "role", ignore = true)
    List<UserResource> userDtoToList(List<User> user);
    UserDto userEntityToDto(User entity);

}
