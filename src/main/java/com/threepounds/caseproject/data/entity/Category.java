package com.threepounds.caseproject.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
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

  @OneToMany(mappedBy = "category")
  private List<Advert> advert;
  @OneToMany(mappedBy = "category")
  private List<Features> features;


  public Category() {

  }

  public Category(UUID id, String name, String description, boolean active, List<Advert> advert) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.active = active;
    this.advert = advert;
  }

}
