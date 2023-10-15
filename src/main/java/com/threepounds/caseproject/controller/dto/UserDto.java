package com.threepounds.caseproject.controller.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;
@Data
public class UserDto {

    private UUID userID;
    private String userName;

    private String name;

    private String email;
    private boolean userActive;
public UserDto() {

}
    public UserDto(UUID userID, String userName, String name, String email, boolean userActive) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.userActive = userActive;
    }


}
