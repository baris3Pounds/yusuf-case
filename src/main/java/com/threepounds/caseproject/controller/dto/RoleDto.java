package com.threepounds.caseproject.controller.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class RoleDto {
    private UUID userId;
    private UUID id;
    private String name;
    private String displayName;

    public RoleDto(UUID id, String name, String displayName) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
    }
    public RoleDto(){

    }
}
