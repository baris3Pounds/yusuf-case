package com.threepounds.caseproject.controller.resource;


import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertResource {
  private UUID id;

  private String title;
  private UserResource creator;

  private String description;
  private ZonedDateTime lastUpdate;
  private ZonedDateTime createdDate;
  private boolean active;
  private BigDecimal price;
  private CategoryResource category;
  private List<String> tags;

  private CityResource city;
  private CountyResource county;
  private StreetResource street;
  private Double latitude;
  private Double longitude;
  private Double distance;
}
