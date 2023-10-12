package com.threepounds.caseproject.controller.resource;

import com.threepounds.caseproject.data.entity.Category;
import lombok.Data;

import java.util.UUID;

public class AdvertResource {
  private UUID id;

  private String title;

  private String description;

  private boolean active;

  private CategoryResource category;

  public AdvertResource( ) {

  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public void setActive(boolean active) {
    this.active = active;
  }

  public CategoryResource getCategory() {
    return category;
  }

  public void setCategory(CategoryResource category) {
    this.category = category;
  }

  public AdvertResource(UUID id, String title, String description, boolean active, CategoryResource category) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.active = active;
    this.category = category;
  }
}
