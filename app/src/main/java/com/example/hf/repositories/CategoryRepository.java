package com.example.hf.repositories;

import com.example.hf.views.data.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

  private static List<Category> srcCategories = new ArrayList<>();

  static {
    srcCategories.add(new Category(1, "Chair"));
    srcCategories.add(new Category(2, "Table"));
    srcCategories.add(new Category(3, "Wallpaper"));
  }

  public static List<Category> getCategories() {
    return srcCategories;
  }

}
