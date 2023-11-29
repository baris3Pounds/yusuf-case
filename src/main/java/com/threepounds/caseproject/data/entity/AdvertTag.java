package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertTag {
    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    @Column
    private UUID advertId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id", referencedColumnName = "tags")
    private Advert advert;




}

