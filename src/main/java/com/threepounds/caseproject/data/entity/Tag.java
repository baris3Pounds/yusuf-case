package com.threepounds.caseproject.data.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Document(indexName = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {
    @Id
    private String id;
    @Field(name = "advert",type = FieldType.Object)
    private Advert advert;
    @Field(name = "advert_tags",type = FieldType.Nested,includeInParent = true)
    private List<AdvertTag> tags=new ArrayList<>();
}
