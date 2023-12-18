package com.threepounds.caseproject.controller.dto;


import com.threepounds.caseproject.data.entity.ESTag;
import com.threepounds.caseproject.data.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDto {
    private UUID id;
    private UUID categoryId;
    private String title;
    private ZonedDateTime lastUpdated;
    private String description;
    private boolean active;
    private List<String> tags;
    private ZonedDateTime createdDate;
    private BigDecimal price;
    private UUID cityId;
    private UUID countyId;
    private UUID streetId;




}







