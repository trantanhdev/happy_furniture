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
    dataset.add(new Product(1, 1, "Wood Chair", "",
        25, "This is a beautiful chair", Place.FLOOR, 1));
    dataset.add(new Product(2, 2, "Wood Table", "",
        100, "This is a wood table", Place.FLOOR, 3));
    dataset.add(new Product(3, 4, "Wall Rug", "",
        35, "This is a Wall Rug", Place.WALL, 2));
    dataset.add(new Product(4, 3, "Bonsai", "",
        20, "This is a Bonsai", Place.FLOOR, 4));
    dataset.add(new Product(5, 3, "Plant", "",
        25, "This is a plant", Place.FLOOR, 5));
  }

  public MutableLiveData<List<Product>> getByPlace(Place place) {
    MutableLiveData<List<Product>> output = new MutableLiveData<>();
    List<Product> tmpProducts =  dataset.stream()
        .filter(p -> place.equals(p.getPlace()))
        .collect(Collectors.toList());
    output.setValue(tmpProducts);
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