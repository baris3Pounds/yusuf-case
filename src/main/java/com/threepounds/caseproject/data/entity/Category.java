package com.threepounds.caseproject.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

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

}
