package com.threepounds.caseproject.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String name;
    private String displayName;

    private List<UUID> permissions;

}
