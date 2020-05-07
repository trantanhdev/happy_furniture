package com.example.hf.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hf.models.Place;
import com.example.hf.models.Product;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.repositories.api.ProductAPIDataSource;
import com.example.hf.repositories.localdata.ProductLocalDataSource;

import java.util.List;

public class ProductsBottomSheetViewModel extends ViewModel {

  private ProductRepository repo;

  public void init() {

    repo = new ProductAPIDataSource();
  }

  public MutableLiveData<List<Product>> getProducts() {
    return repo.getByPlace(Place.FLOOR);
  }

}
