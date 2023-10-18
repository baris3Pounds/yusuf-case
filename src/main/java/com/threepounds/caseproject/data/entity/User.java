package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;
@Data
@Entity(name = "users")
public class User {


    @Id
    @GeneratedValue
    @Column
    private UUID id;
    @Column
    private String userName;
    @Column
    private String name;
    @Column
    @NotNull
    private String email;
    @Column
    private boolean userActive;
    @OneToMany(mappedBy = "user")
    private List<Role> roles;

    // TODO OneToMany 1 user 1 den fazla roles olabilir

    public User(){

    }
    public User(UUID id, String userName, String name, String email, boolean userActive) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.userActive = userActive;
    }
}

