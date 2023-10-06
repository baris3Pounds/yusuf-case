package com.threepounds.caseproject.service;


import com.threepounds.caseproject.data.entity.Category;
import com.threepounds.caseproject.data.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private final CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public Category save(Category category){
    return repository.save(category);
  }

  public Optional<Category> getById(UUID categoryId){
    return repository.findById(categoryId);
  }

  public List<Category> list(){
    return repository.findAll();
  }

  public void delete(UUID categoryId){
    repository.deleteById(categoryId);
  }
}
