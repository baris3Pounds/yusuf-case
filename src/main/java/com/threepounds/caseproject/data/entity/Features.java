package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "features")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
