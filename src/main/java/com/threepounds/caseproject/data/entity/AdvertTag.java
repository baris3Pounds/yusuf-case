package com.threepounds.caseproject.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "advert_tags")
public class AdvertTag {
    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Field(name = "tag",type = FieldType.Text)
    @Column
    private String name;
    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "advert_id", referencedColumnName = "id")
    private Advert advert;




}

