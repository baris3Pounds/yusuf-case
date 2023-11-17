package com.threepounds.caseproject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

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
    private ZonedDateTime createdDate;
    private BigDecimal price;



}







