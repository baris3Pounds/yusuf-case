package com.threepounds.caseproject.controller.dto;


import lombok.Data;

import java.util.UUID;
@Data
public class PermissionDto {
    private UUID id;
    private UUID roleId;
    private String name;
    private String displayName;
    public PermissionDto(UUID id, String name, String displayName) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
    }
    public PermissionDto(){

    }
}
