package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.resource.AdvertResource;
import com.threepounds.caseproject.data.entity.Advert;
import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertMapper {


   Advert advertDtoToEntity(AdvertDto advertDto);

   AdvertResource entityToAdvertResource(Advert advert);

   List<AdvertResource> entityToAdvertResource(List<Advert> advert);


}
