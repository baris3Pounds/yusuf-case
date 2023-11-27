package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    private int advertId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id", referencedColumnName = "tags")
    private Advert advert;




}

