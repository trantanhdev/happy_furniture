package com.example.hf.repositories.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hf.api.CategoryAPI;
import com.example.hf.api.models.CategoryAPIRes;
import com.example.hf.api.RetrofitClientInstance;
import com.example.hf.models.Category;
import com.example.hf.repositories.CategoryRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAPIDataSource implements CategoryRepository {

  CategoryAPI categoryAPI = RetrofitClientInstance.getRetrofitInstance().create(CategoryAPI.class);

  public CategoryAPIDataSource() {
    getCategories();
  }

  @Override
  public MutableLiveData<List<Category>> get() {
    return null;
  }

  @Override
  public Category get(int id) {
    return null;
  }

  private void getCategories() {

    Call<List<CategoryAPIRes>> call = categoryAPI.listRepos();
    call.enqueue(new Callback<List<CategoryAPIRes>>() {
      @Override
      public void onResponse(Call<List<CategoryAPIRes>> call,
                             Response<List<CategoryAPIRes>> response) {
        Log.i("info","data loaded");
      }

      @Override
      public void onFailure(Call<List<CategoryAPIRes>> call, Throwable t) {
        Log.i("error","data not loaded");
      }
    });
  }

}
