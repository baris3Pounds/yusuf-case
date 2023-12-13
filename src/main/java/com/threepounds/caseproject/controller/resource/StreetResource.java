package com.threepounds.caseproject.controller.resource;

import com.threepounds.caseproject.data.entity.adress.Street;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreetResource {
  private UUID id;

  private String name;

  private List<Street> streets;

}
