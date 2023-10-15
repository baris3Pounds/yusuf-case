package com.threepounds.caseproject.controller.resource;

import lombok.Data;

import java.util.UUID;


@Data
public class CategoryResource {
  private UUID id;
  private String name;
  private String description;

  private AdvertResource advert;
  private FeaturesResource features;

  public CategoryResource( ) {

  }

  public CategoryResource(UUID id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }


}
