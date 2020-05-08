package com.example.hf.api;

import com.example.hf.api.models.ProductAPIRes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Product API client
 */
public interface ProductAPI {

  @GET("products.json")
  Call<List<ProductAPIRes>> fetchProducts();
}