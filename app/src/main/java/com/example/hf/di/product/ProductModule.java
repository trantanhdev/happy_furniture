package com.example.hf.di.product;

import com.example.hf.repositories.ProductRepository;
import com.example.hf.repositories.lds.ProductLocalDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductModule {

  @Provides
  ProductRepository getProductRepository() {
    return new ProductLocalDataSource();
  }
}
