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
  @ManyToMany
  @JoinTable(
      name = "role_permissions",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private List<Permission> permissions;
  @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
  private List<User> users;

  // TODO ManyToOne  1 den fazla role 1 user a ait olabilir

  // TODO OneToMany  1 role a ait 1 den fazla permissions olabilir

}
