package com.example.hf.repositories;

import com.example.hf.models.Category;

import java.util.List;

public interface CategoryRepository {

  public List<Category> get();

  public Category get(int id);
}
