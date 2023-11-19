package com.threepounds.caseproject.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "category")
@NoArgsConstructor
@AllArgsConstructor
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






}
