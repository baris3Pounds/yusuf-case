package com.threepounds.caseproject.controller.resource;

import lombok.Data;

import java.util.UUID;
@Data
public class RoleResource {

    private UUID id;
    private String name;
    private String displayName;

    public RoleResource(UUID id, String name, String displayName) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;

    }
    public RoleResource(){

    }
}
