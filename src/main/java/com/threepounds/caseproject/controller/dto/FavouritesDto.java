package com.threepounds.caseproject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouritesDto {

    private UUID id;
    private UUID advert_id;
    private UUID user_id;
    private ZonedDateTime favourited_date;

}
