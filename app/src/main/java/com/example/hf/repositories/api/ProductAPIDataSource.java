package com.example.hf.repositories.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hf.api.ProductAPI;
import com.example.hf.api.RetrofitClientInstance;
import com.example.hf.api.models.ProductAPIRes;
import com.example.hf.models.Place;
import com.example.hf.models.Product;
import com.example.hf.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAPIDataSource implements ProductRepository {

  ProductAPI api = RetrofitClientInstance.getRetrofitInstance().create(ProductAPI.class);

  @Override
  public MutableLiveData<List<Product>> getByPlace(Place place) {
    MutableLiveData<List<Product>> output = new MutableLiveData<>();

    Call<List<ProductAPIRes>> call = api.fetchProducts();
    call.enqueue(new Callback<List<ProductAPIRes>>() {
      @Override
      public void onResponse(Call<List<ProductAPIRes>> call,
                             Response<List<ProductAPIRes>> response) {
        List<Product> tmpProducts =
        response.body().stream()
            .filter(p -> {
              Place attemptPlace = Place.valueOf(p.getPlace());
              boolean isPassed = place.equals(attemptPlace);
              return isPassed;
            })
            .map(ProductAPIDataSource.this::fetchToProduct)
            .collect(Collectors.toList());
        output.setValue(tmpProducts);
      }

      @Override
      public void onFailure(Call<List<ProductAPIRes>> call, Throwable t) {
        Log.i("error","data not loaded");
      }
    });

    return output;
  }

  @Override
  public MutableLiveData<List<Product>> getProducts() {
    MutableLiveData<List<Product>> output = new MutableLiveData<>();

    Call<List<ProductAPIRes>> call = api.fetchProducts();
    call.enqueue(new Callback<List<ProductAPIRes>>() {
      @Override
      public void onResponse(Call<List<ProductAPIRes>> call,
                             Response<List<ProductAPIRes>> response) {
        List<Product> tmpProducts = new ArrayList<>();
        response.body().stream().forEach(productAPIRes -> {
          tmpProducts.add(fetchToProduct(productAPIRes));
        });

        output.setValue(tmpProducts);
      }

      @Override
      public void onFailure(Call<List<ProductAPIRes>> call, Throwable t) {
        Log.i("error","data not loaded");
      }
    });

    return output;
  }

  @Override
  public MutableLiveData<Product> getProduct(int id) {
    MutableLiveData<Product> output = new MutableLiveData<>();

    Call<List<ProductAPIRes>> call = api.fetchProducts();
    call.enqueue(new Callback<List<ProductAPIRes>>() {
      @Override
      public void onResponse(Call<List<ProductAPIRes>> call,
                             Response<List<ProductAPIRes>> response) {

        Optional<ProductAPIRes> optProduct = response.body().stream()
            .filter(p -> p.getId() == id)
            .findFirst();
        if (optProduct.isPresent()) {
          output.setValue(fetchToProduct(optProduct.get()));
        } else {
          output.setValue(null);
        }
      }

      @Override
      public void onFailure(Call<List<ProductAPIRes>> call, Throwable t) {
        Log.i("error","data not loaded");
      }
    });

    return output;
  }

  /**
   * fetch to Product
   *
   * @param resProduct
   * @return
   */
  private Product fetchToProduct(ProductAPIRes resProduct) {
    Integer id = resProduct.getId();
    Integer categoryId = resProduct.getCategoryId();
    String name = resProduct.getName();
    String profileImage = resProduct.getProfileImage();
    Integer price = resProduct.getPrice();
    String desc = resProduct.getDesc();
    Place place = Place.valueOf(resProduct.getPlace());
    Integer arModelId = resProduct.getArModelId();
    return new Product(id, categoryId, name, profileImage, price, desc, place, arModelId);
  }
}
