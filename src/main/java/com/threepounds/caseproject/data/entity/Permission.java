package com.threepounds.caseproject.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity(name = "permission")
public class Permission {
  @Id
  @GeneratedValue
  @Column
  private UUID id;

  @Column
  private String name;

  @Column
  private String displayName;


  // TODO ManyToOne  1 den fazla permissions 1 role ait olabilir


}
