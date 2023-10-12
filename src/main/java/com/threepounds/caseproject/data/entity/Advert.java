package com.threepounds.caseproject.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity(name = "advert")
@Data
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
  @CreationTimestamp
  private ZonedDateTime createdDate;

  @Column
  @UpdateTimestamp
  private ZonedDateTime lastUpdated;

  @Column
  private BigDecimal price;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private Category category;

  public void setCreatedDate(ZonedDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }



  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public ZonedDateTime getCreatedDate() {
    return createdDate;
  }

  public ZonedDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(ZonedDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Advert() {

  }



  public Advert(UUID id, String title, String description, boolean active,
      ZonedDateTime createdDate, BigDecimal price) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.active = active;
    this.createdDate = createdDate;
    this.price = price;
  }




}