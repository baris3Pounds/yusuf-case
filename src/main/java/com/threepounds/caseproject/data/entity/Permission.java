package com.threepounds.caseproject.data.entity;


import jakarta.persistence.*;

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
  @ManyToOne
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;


  // TODO ManyToOne  1 den fazla permissions 1 role ait olabilir


}
