package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.FeaturesDto;
import com.threepounds.caseproject.controller.resource.FeaturesResource;
import com.threepounds.caseproject.data.entity.Features;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FeaturesMapper {
    Features dtoToEntity(FeaturesDto FeaturesDto);

    FeaturesResource featureDto(Features features);
}
