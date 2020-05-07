package com.example.hf.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.hf.models.Place;
import com.example.hf.models.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository {

  public MutableLiveData<List<Product>> getByPlace(Place place);

  public MutableLiveData<List<Product>> getProducts();

  public MutableLiveData<Product> getProduct(int id);
}
