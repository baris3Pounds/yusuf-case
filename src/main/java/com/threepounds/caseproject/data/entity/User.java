package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.lang.NonNull;

import java.util.UUID;
@Data
@Entity(name = "users")
public class User {
    public User(){

    }


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

    // TODO OneToMany 1 user 1 den fazla roles olabilir


}

