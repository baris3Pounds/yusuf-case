package com.threepounds.caseproject.controller.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID userId;
    private String username;

    private String name;

    private String email;

    private String password;

    private boolean userActive;
    private List<UUID> roles;


}
