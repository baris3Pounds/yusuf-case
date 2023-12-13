package com.threepounds.caseproject.data.entity.adress;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "street")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Street {

  @Id
  @Column
  @GeneratedValue
  private UUID id;
  @Column
  private String name;
  @Column
  private UUID county_id;





}
