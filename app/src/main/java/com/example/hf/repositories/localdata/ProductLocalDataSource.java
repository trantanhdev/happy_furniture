package com.example.hf.repositories.localdata;

import androidx.lifecycle.MutableLiveData;

import com.example.hf.R;
import com.example.hf.models.Place;
import com.example.hf.models.Product;
import com.example.hf.repositories.CategoryRepository;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.repositories.localdata.CategoryLocalDataSouce;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductLocalDataSource implements ProductRepository {

  private static ArrayList<Product> dataset = new ArrayList<>();

  static{
    dataset.add(new Product(1, 1, "Wood Chair", R.drawable.f_1_1,
        25, "This is a beautiful chair", Place.FLOOR, 1));
    dataset.add(new Product(2, 2, "Wood Table", R.drawable.f_2_1,
        100, "This is a wood table", Place.FLOOR, 3));
    dataset.add(new Product(3, 4, "Wall Rug", R.drawable.f_3_1,
        35, "This is a Wall Rug", Place.WALL, 2));
    dataset.add(new Product(4, 3, "Bonsai", R.drawable.pi_bonsai,
        20, "This is a Bonsai", Place.FLOOR, 4));
    dataset.add(new Product(5, 3, "Plant", R.drawable.pi_plant,
        25, "This is a plant", Place.FLOOR, 5));
  }

  public ArrayList<Product> get() {
    return dataset;
  }

  public Product get(int id) {
    Optional<Product> optProduct = dataset.stream()
        .filter(p -> p.getId() == id)
        .findFirst();
    if (optProduct.isPresent()) {
      return optProduct.get();
    }

    return null;
  }

  public List<Product> getByPlace(Place place) {
    List<Product> output =  dataset.stream()
        .filter(p -> place.equals(p.getPlace()))
        .collect(Collectors.toList());

    return output;
  }

  public MutableLiveData<List<Product>> getProducts() {
    MutableLiveData<List<Product>> products = new MutableLiveData<>();
    products.setValue(dataset);
    return products;
  }

  public MutableLiveData<Product> getProduct(int id) {
    MutableLiveData<Product> product = new MutableLiveData<>();

    Optional<Product> optProduct = dataset.stream()
        .filter(p -> p.getId() == id)
        .findFirst();
    if (optProduct.isPresent()) {
      product.setValue(optProduct.get());
    }

    return product;
  }

}