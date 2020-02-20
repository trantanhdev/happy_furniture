package com.example.hf.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hf.models.Product;

import java.util.List;

public class HomeViewModel extends ViewModel {

  private MutableLiveData<String> mText;
  private MutableLiveData<List<Product>> mProducts;

  public HomeViewModel() {
    mText = new MutableLiveData<>();
    mProducts = new MutableLiveData<>();
  }

  public LiveData<String> getText() {
    return mText;
  }

  public LiveData<List<Product>> getProducts() {
    return mProducts;
  }
}