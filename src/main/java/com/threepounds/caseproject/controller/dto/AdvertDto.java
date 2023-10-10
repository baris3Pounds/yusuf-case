package com.threepounds.caseproject.controller.dto;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class AdvertDto {
    private UUID id;

    private String title;
    private Date lastUpdated;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    private String description;

    private boolean active;

    private Date date;
    public AdvertDto(){

    }

    public UUID getId() {
        return id;
    }

    public AdvertDto(UUID id, String title, String description, boolean active, Date date, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.active = active;
        this.date = date;
        this.price = price;
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


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    private BigDecimal price;


}

