package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.lang.NonNull;

import java.util.UUID;
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column
    private UUID userID;
    @Column
    private String userName;
    @Column
    private String name;
    @Column
    @NotNull
    private String email;
    @Column
    private boolean userActive;

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

