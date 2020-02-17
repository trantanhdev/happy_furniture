package com.example.hf.repositories;

import com.example.hf.models.data.Product;

import java.util.List;

public interface ProductRepository {

  public List<Product> get();

  public List<Product> getByCategory(int categoryId);
}
