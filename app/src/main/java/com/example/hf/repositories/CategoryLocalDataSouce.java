package com.example.hf.repositories;

import com.example.hf.models.Category;
import com.example.hf.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryLocalDataSouce implements CategoryRepository {

  private static List<Category> srcCategories = new ArrayList<>();

  static {
    srcCategories.add(new Category(1, "Chair"));
    srcCategories.add(new Category(2, "Table"));
    srcCategories.add(new Category(3, "Rug"));
  }

  public List<Category> get() {
    return srcCategories;
  }
}
