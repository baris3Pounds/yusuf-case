package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.CategoryDto;
import com.threepounds.caseproject.controller.resource.CategoryResource;
import com.threepounds.caseproject.data.entity.Category;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToEntity(CategoryDto dto);
    CategoryResource categoryDto(Category category);
}
