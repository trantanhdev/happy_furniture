package com.example.hf.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.hf.models.Category;

import java.util.List;

public interface CategoryRepository {

  public MutableLiveData<List<Category>> get();

  public Category get(int id);
}
