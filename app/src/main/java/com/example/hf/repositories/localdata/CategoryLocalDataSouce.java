package com.example.hf.repositories.localdata;

import androidx.lifecycle.MutableLiveData;

import com.example.hf.models.Category;
import com.example.hf.models.Place;
import com.example.hf.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryLocalDataSouce implements CategoryRepository {

  private static List<Category> dataset = new ArrayList<>();

  static {
    dataset.add(new Category(1, "Chair"));
    dataset.add(new Category(2, "Table"));
    dataset.add(new Category(3, "Plant"));
    dataset.add(new Category(4, "Rug"));
  }

  public MutableLiveData<List<Category>> get() {
    MutableLiveData<List<Category>> categories = new MutableLiveData<>();
    categories.setValue(dataset);
    return categories;
  }

  public Category get(int id) {
    Optional<Category> optCategory = dataset.stream()
        .filter(p -> p.getId() == id)
        .findFirst();
    if (optCategory.isPresent()) {
      return optCategory.get();
    }

    return null;
  }
}
