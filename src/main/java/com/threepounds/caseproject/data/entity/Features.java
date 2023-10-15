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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
