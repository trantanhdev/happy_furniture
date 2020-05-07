package com.example.hf.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hf.models.Product;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.repositories.localdata.ProductLocalDataSource;

import java.util.List;

public class ProductsViewModel extends ViewModel {

  private MutableLiveData<List<Product>> mProducts;
  private ProductRepository repo;

  public void init() {
    if (mProducts != null) {
      return;
    }

    repo = new ProductLocalDataSource();
    mProducts = repo.getProducts();
  }

  public MutableLiveData<List<Product>> getProducts() {
    return mProducts;
  }

}
