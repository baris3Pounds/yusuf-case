package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.data.entity.Advert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvertMapper {
   Advert advertDtoToEntity(AdvertDto advertDto);
   AdvertDto entitiyToAdvertDto(Advert advert);
}
