package com.threepounds.caseproject.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID userId;
    private String userName;

    private String name;

    private String email;
    private boolean userActive;
    private List<UUID> roles;


}
