package com.threepounds.caseproject.controller.resource;

import com.threepounds.caseproject.data.entity.Category;
import lombok.Data;

import java.util.UUID;
@Data
public class AdvertResource {
  private UUID id;

  private String title;

  private String description;

  private boolean active;

  private CategoryResource category;

  public AdvertResource( ) {

  }



  public AdvertResource(UUID id, String title, String description, boolean active, CategoryResource category) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.active = active;
    this.category = category;
  }
}
