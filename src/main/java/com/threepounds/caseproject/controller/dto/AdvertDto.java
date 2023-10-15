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
    private String description;

    private boolean active;

    private Date date;
    private BigDecimal price;

    public AdvertDto() {

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







