package com.example.hf.repositories;

import com.example.hf.models.data.Product;

import java.util.ArrayList;

public interface ProductRepository {

  public ArrayList<Product> get();

  public Product get(int id);
  //public ArrayList<Product> getByCategory(int categoryId);
}
