package com.threepounds.caseproject.controller.mapper;

import com.threepounds.caseproject.controller.dto.AdvertDto;
import com.threepounds.caseproject.controller.resource.*;
import com.threepounds.caseproject.data.entity.Advert;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.data.entity.adress.City;
import com.threepounds.caseproject.data.entity.adress.County;
import com.threepounds.caseproject.data.entity.adress.Street;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.CityService;
import com.threepounds.caseproject.service.CountyService;
import com.threepounds.caseproject.service.StreetService;
import java.util.List;
import java.util.Optional;

import com.threepounds.caseproject.service.UserService;
import org.hibernate.service.spi.InjectService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AdvertMapper {

   @Autowired
   private UserService userService;

   @Autowired
   private UserMapper userMapper;

   @Autowired
   private CityService cityService;

   @Autowired
   private CountyService countyService;

   @Autowired
   private StreetService streetService;

   @Autowired
   private CityMapper cityMapper;

   @Autowired
   private CountyMapper countyMapper;

   @Autowired
   private StreetMapper streetMapper;


   public abstract Advert advertDtoToEntity(AdvertDto advertDto);

   public abstract AdvertResource entityToAdvertResource(Advert advert);

   public abstract List<AdvertResource> entityToAdvertResource(List<Advert> advert);

   @AfterMapping
   void afterResourceMapping(Advert entity, @MappingTarget AdvertResource resource) {

      City city = cityService.getById(entity.getCityId()).orElseThrow( ()-> new NotFoundException("City not found"));
      CityResource cityResource = cityMapper.entityToCityResource(city);

      County county = countyService.getById(entity.getCountyId()).orElseThrow( ()-> new NotFoundException("County not found"));
      CountyResource countyResource = countyMapper.entityToCountyResource(county);

      Street street = streetService.getById(entity.getStreetId()).orElseThrow( ()-> new NotFoundException("Street not found"));
      StreetResource streetResource = streetMapper.entityToStreetResource(street);

      User user = userService.getByUserId(entity.getCreatorId()).orElseThrow( ()-> new NotFoundException("User not found"));
      UserResource userResource = userMapper.userDto(user);


      resource.setCity(cityResource);
      resource.setCounty(countyResource);
      resource.setStreet(streetResource);
      resource.setCreator(userResource);

   }


}
