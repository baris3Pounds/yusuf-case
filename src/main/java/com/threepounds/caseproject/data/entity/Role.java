package com.threepounds.caseproject.data.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
@Entity(name = "roles")
public class Role {
  @Id
  @GeneratedValue
  @Column
  private UUID id;

  @Column
  private String name;

  @Column
  private String displayName;
  @OneToMany(mappedBy = "role")
  private List<Permission> permissions;
  @ManyToOne
  @JoinColumn(name = "user_id",referencedColumnName = "id")
  private User user;

  // TODO ManyToOne  1 den fazla role 1 user a ait olabilir

  // TODO OneToMany  1 role a ait 1 den fazla permissions olabilir

}
