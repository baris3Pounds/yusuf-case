package com.threepounds.caseproject.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity(name = "users")
public class Role {
  @Id
  @GeneratedValue
  @Column
  private UUID id;

  @Column
  private String name;

  @Column
  private String displayName;

  // TODO ManyToOne  1 den fazla role 1 user a ait olabilir

  // TODO OneToMany  1 role a ait 1 den fazla permissions olabilir

}
