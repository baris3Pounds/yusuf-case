package com.threepounds.caseproject.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResource {
  private UUID id;
  private String name;
  private String description;





}
