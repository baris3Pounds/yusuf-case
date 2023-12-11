package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "favourites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favourites {
    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Column
    private UUID userId;
    @Column
    private UUID advertId;
    @Column
    @CreationTimestamp
    private ZonedDateTime favouritedDate;



}
