package com.threepounds.caseproject.data.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.UUID;

@Document(indexName = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ESTag{
    @Id
    private String id;
    @Field(name = "tags",type = FieldType.Text)
    private String Tag;

}
