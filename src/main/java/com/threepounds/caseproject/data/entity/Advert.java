package com.threepounds.caseproject.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity(name = "advert")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document(indexName = "advert", shards = -1, replicas = 0, refreshInterval = "-1") bu annotation elasticsearch için tag sınıfına eklenmeli.
public class Advert {

  @Id
  @Column
  @GeneratedValue
  private UUID id;
  @Column
  private String title;
  @Column
  private String description;
  @Column
  private boolean active;
  @Column
  private String tags;
  @Column
  @CreationTimestamp
  private ZonedDateTime createdDate;

  @Column
  @UpdateTimestamp
  private ZonedDateTime lastUpdated;

  @Column
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "category_id",referencedColumnName = "id")
  private Category category;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "tags", referencedColumnName = "id")
  private AdvertTag advertTag;


}
