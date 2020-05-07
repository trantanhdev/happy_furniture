package com.example.hf.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hf.models.Product;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.repositories.localdata.ProductLocalDataSource;

public class ProductViewModel extends ViewModel {

  private MutableLiveData<Product> product;
  private ProductRepository repo;

  public void init(int productId) {
    if (product != null) {
      return;
    }

    repo = new ProductLocalDataSource();
    product = repo.getProduct(productId);
  }

  public MutableLiveData<Product> getProduct() {
    return product;
  }

}
