package com.example.hf.api;

import com.example.hf.api.models.CategoryAPIRes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {

  @GET("categories.json")
  Call<List<CategoryAPIRes>> listRepos();
}
