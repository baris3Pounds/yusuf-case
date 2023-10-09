package com.threepounds.caseproject.controller;


import com.threepounds.caseproject.controller.dto.CategoryDto;
import com.threepounds.caseproject.controller.mapper.CategoryMapper;
import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.CategoryService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/categories")
@RestController
public class CategoryController {

  private final CategoryService categoryService;

  private final CategoryMapper categoryMapper;

  public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
    this.categoryService = categoryService;
    this.categoryMapper = categoryMapper;
  }


  @GetMapping("")
  public ResponseEntity<List<Category>> list(){
    return ResponseEntity.ok(categoryService.list());
  }

  @PostMapping("")
  public ResponseEntity<Category> create(@RequestBody CategoryDto categoryDto){
    Category categoryToSave = categoryMapper.dtoToEntity(categoryDto);
    Category savedCategory = categoryService.save(categoryToSave);
    return ResponseEntity.ok(savedCategory);
  }

  // TODO PutMapping mevcut kategoriyi güncellemeli
  // categorinin id si kontrol edilir databasede var mı
  // mapperdan dto entitye çevrilmeli
  // save edilir.
  @PutMapping("{id}")
  public ResponseEntity update(@PathVariable UUID id, @RequestBody CategoryDto dto){
    Category existingCategory = categoryService.getById(id)
        .orElseThrow(() -> new NotFoundException("Category not found"));

    Category mappedCategory = categoryMapper.dtoToEntity(dto);
    mappedCategory.setId(existingCategory.getId());
    Category updatedCategory = categoryService.save(mappedCategory);
    return ResponseEntity.ok(updatedCategory);
  }

  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable UUID id){
    categoryService.delete(id);
    return ResponseEntity.ok("success");
  }
  @GetMapping("{id}")
  public ResponseEntity getOneCategory(@PathVariable UUID id){
    categoryService.getById(id)
            .orElseThrow(()-> new NotFoundException("Category not found"));
    return ResponseEntity.ok(categoryService.getById(id));
  }

}
