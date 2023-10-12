package com.threepounds.caseproject.data.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Entity(name = "category")
public class Category {

  @Id
  @GeneratedValue
  @Column
  private UUID id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private boolean active;
  @OneToOne(mappedBy = "category")
  private Advert advert;

  public Category(Advert advert) {
    this.advert = advert;
  }

  public Category() {

  }

  public Category(UUID id, String name, String description, boolean active, Advert advert) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.active = active;
    this.advert = advert;
  }

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

  public void setActive(boolean active) {
    this.active = active;
  }

  public Advert getAdvert() {
    return advert;
  }

  public void setAdvert(Advert advert) {
    this.advert = advert;
  }
}
