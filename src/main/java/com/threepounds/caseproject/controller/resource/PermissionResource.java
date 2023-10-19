package com.threepounds.caseproject.controller.resource;

import lombok.Data;

@Data
public class PermissionResource {

    private String id;
    private String name;
    private String displayName;

    public PermissionResource(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
    public PermissionResource(){

    }
}
