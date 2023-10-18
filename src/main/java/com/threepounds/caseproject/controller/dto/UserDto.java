package com.threepounds.caseproject.controller.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;
@Data
public class UserDto {

    private UUID userId;
    private String userName;

    private String name;

    private String email;
    private boolean userActive;
public UserDto() {

}
    public UserDto(UUID userId, String userName, String name, String email, boolean userActive) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.userActive = userActive;
    }


}
