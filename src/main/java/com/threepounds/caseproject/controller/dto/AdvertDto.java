package com.threepounds.caseproject.controller.dto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Data
public class AdvertDto {
    private UUID id;

    private UUID categoryId;

    private String title;
    private Date lastUpdated;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
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

    private String description;

    private boolean active;

    private Date date;
    private BigDecimal price;

    public AdvertDto() {

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
}







