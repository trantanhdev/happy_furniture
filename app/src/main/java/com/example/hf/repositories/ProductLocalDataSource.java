package com.example.hf.repositories;

import com.example.hf.R;
import com.example.hf.models.Place;
import com.example.hf.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductLocalDataSource implements ProductRepository {

  private static ArrayList<Product> srcProducts = new ArrayList<>();
  private static CategoryRepository categoryRepository = new CategoryLocalDataSouce();

  static{

    srcProducts.add(new Product(1, 1, "Wood Chair", R.drawable.f_1_1,
        new int[] {},25, "This is a beautiful chair", Place.FLOOR, 1));
    srcProducts.add(new Product(2, 2, "Wood Table", R.drawable.f_2_1,
        new int[] {},100, "This is a wood table", Place.FLOOR, 3));
    srcProducts.add(new Product(3, 2, "Wall Rug", R.drawable.f_3_1,
        new int[] {},35, "This is a Wall Rug", Place.WALL, 2));
  }

  public ArrayList<Product> get() {
    return srcProducts;
  }

  public Product get(int id) {
    Optional<Product> optProduct = srcProducts.stream()
        .filter(p -> p.getId() == id)
        .findFirst();
    if (optProduct.isPresent()) {
      return optProduct.get();
    }

    return null;
  }

  public List<Product> getByPlace(Place place) {
    List<Product> output =  srcProducts.stream()
        .filter(p -> place.equals(p.getPlace()))
        .collect(Collectors.toList());

    return output;
  }

}