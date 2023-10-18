package com.threepounds.caseproject.controller.resource;

import lombok.Data;

import java.util.UUID;
@Data
public class UserResource {
    private UUID id;
    private String userName;

    private String name;

    private String email;

    public UserResource(UUID id, String userName, String name, String email) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
    }
    public UserResource(){

    }
}
