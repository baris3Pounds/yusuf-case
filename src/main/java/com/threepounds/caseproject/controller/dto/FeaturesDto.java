package com.threepounds.caseproject.controller.dto;
import lombok.Data;

import java.util.UUID;
@Data
public class FeaturesDto {
    private UUID categoryId;

    private UUID id;

    private String title;

    private boolean active;

    public FeaturesDto(UUID id, String title, boolean active) {
        this.id = id;
        this.title = title;
        this.active = active;
    }
    public FeaturesDto(){

    }

}
