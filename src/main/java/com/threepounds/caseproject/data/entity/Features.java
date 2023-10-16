package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "features")
@Data
public class Features {
    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String title;
    @Column
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    public Features(UUID id, String title, boolean active) {
        this.id = id;
        this.title = title;
        this.active = active;
    }
    public Features(){

    }
}
