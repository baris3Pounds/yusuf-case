package com.threepounds.caseproject.controller.resource;

import com.threepounds.caseproject.data.entity.Category;
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

  private boolean active;

  private CategoryResource category;



}
