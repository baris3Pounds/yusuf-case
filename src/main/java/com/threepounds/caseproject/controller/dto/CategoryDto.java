package com.threepounds.caseproject.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDto {

  private UUID id;
  private String name;
  private String description;
  private boolean active;


  public CategoryDto(UUID id, String name, String description, boolean active) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.active = active;
  }

}
