package com.threepounds.caseproject.controller.resource;

import lombok.Data;

import java.util.UUID;
@Data
public class FeaturesResource {
    private UUID id;

    private String title;

    public FeaturesResource(UUID id, String title) {
        this.id = id;
        this.title = title;
    }
    public FeaturesResource(){}
}
