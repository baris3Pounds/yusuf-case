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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
