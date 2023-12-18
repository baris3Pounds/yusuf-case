package com.threepounds.caseproject.data.entity.adress;

import com.threepounds.caseproject.data.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

  @Id
  @Column
  @GeneratedValue
  private UUID id;
  @Column
  private String name;


  @OneToMany
  private List<County> counties;


}
