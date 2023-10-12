package com.threepounds.caseproject.controller.dto;

import lombok.Data;

import java.util.UUID;

public class CategoryDto {

  private UUID id;
  private String name;
  private String description;
  private boolean active;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isActive() {
    return active;
  }

  public CategoryDto(UUID id, String name, String description, boolean active) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.active = active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
