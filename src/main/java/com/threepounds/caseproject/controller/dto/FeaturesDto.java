package com.threepounds.caseproject.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeaturesDto {
    private UUID categoryId;

    private UUID id;

    private String title;

    private boolean active;



}
