package com.threepounds.caseproject.controller.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

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

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUserActive() {
        return userActive;
    }

    public void setUserActive(boolean userActive) {
        this.userActive = userActive;
    }
}
