package com.threepounds.caseproject.controller.dto;

import jakarta.persistence.Column;

public class UserDto {
    private String userName;

    private String name;

    private String email;
    private boolean userActive;

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

    public String getEMail() {
        return email;
    }

    public void setEMail(String eMail) {
        this.email = eMail;
    }

    public boolean isUserActive() {
        return userActive;
    }

    public void setUserActive(boolean userActive) {
        this.userActive = userActive;
    }



}
