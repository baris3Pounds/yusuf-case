package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.UserDto;
import com.threepounds.caseproject.data.entity.User;
import org.mapstruct.Mapper;

import javax.swing.text.html.parser.ContentModel;

@Mapper(componentModel="spring")
public interface UserMapper {
    User userDtoToEntity(UserDto userDto);
}
