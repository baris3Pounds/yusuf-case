package com.threepounds.caseproject.controller;

import com.threepounds.caseproject.controller.dto.CityDto;
import com.threepounds.caseproject.controller.dto.CountyDto;
import com.threepounds.caseproject.controller.mapper.CountyMapper;
import com.threepounds.caseproject.controller.resource.CityResource;
import com.threepounds.caseproject.controller.resource.CountyResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.data.entity.adress.City;
import com.threepounds.caseproject.data.entity.adress.County;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.CityService;
import com.threepounds.caseproject.service.CountyService;
import org.apache.el.stream.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/county")
public class CountyController {
    private final CountyService countyService;
    private final CountyMapper countyMapper;

    public CountyController(CountyService countyService, CountyMapper countyMapper) {
        this.countyService = countyService;
        this.countyMapper = countyMapper;
    }


    @PostMapping("")
    public ResponseEntity<County> createCounty(@RequestBody CountyDto countyDto){
        County county = countyMapper.countyDtoToEntity(countyDto);
        County savedCounty=countyService.save(county);
        CountyResource countyResource = countyMapper.entityToCountyResource(savedCounty);

        return new ResponseEntity<>(savedCounty, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        countyService.delete(id);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/{id}")
    public ResponseModel<CountyResource> getOneCounty(@PathVariable UUID id){
        County county= countyService.getById(id)
                .orElseThrow(()-> new IllegalArgumentException() );
        CountyResource countyResource = countyMapper.entityToCountyResource(county);
        return new ResponseModel<>(HttpStatus.OK.value(),countyResource,null);
    }

    @GetMapping("")
    public ResponseEntity<List<CountyResource>> listCounties(){
        List<CountyResource> countyResource = countyMapper.entityToCountyResource(countyService.getAllCounties());

        return ResponseEntity.ok(countyResource);
    }


}