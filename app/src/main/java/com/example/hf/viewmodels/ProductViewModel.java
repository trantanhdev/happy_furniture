package com.example.hf.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hf.models.Product;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.repositories.api.ProductAPIDataSource;

public class ProductViewModel extends ViewModel {

  private ProductRepository repo;

  public void init() {
    repo = new ProductAPIDataSource();
  }

  public MutableLiveData<Product> getProduct(int productId) {
    return repo.getProduct(productId);
  }

}
