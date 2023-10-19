package com.threepounds.caseproject.data.entity;


import jakarta.persistence.*;

import java.util.List;
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
  @ManyToMany(fetch = FetchType.LAZY,mappedBy = "permissions")
  private List<Role> roles;


  // TODO ManyToOne  1 den fazla permissions 1 role ait olabilir


}
