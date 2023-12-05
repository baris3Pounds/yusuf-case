package com.threepounds.caseproject.controller.resource;

import com.threepounds.caseproject.data.entity.Category;
import java.util.List;

import com.threepounds.caseproject.data.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertResource {
  private UUID id;

  private String title;

  private String description;

  private boolean active;

  private CategoryResource category;

  private List<String> tags;

}
