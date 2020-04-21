package com.example.hf.repositories;

import com.example.hf.models.Category;
import com.example.hf.models.Place;
import com.example.hf.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

  public Category get(int id) {
    Optional<Category> optCategory = srcCategories.stream()
        .filter(p -> p.getId() == id)
        .findFirst();
    if (optCategory.isPresent()) {
      return optCategory.get();
    }

    return null;
  }
}
