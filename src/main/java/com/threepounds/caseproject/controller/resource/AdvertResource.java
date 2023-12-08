package com.threepounds.caseproject.controller.resource;

import com.threepounds.caseproject.data.entity.Category;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import com.threepounds.caseproject.data.entity.Tag;
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

  private String description;
  private ZonedDateTime lastUpdate;
  private ZonedDateTime createdDate;
  private boolean active;
  private BigDecimal price;
  private CategoryResource category;
  private List<String> tags;

}
