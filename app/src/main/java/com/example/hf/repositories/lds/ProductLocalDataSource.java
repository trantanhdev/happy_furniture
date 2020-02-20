package com.example.hf.repositories.lds;

import com.example.hf.R;
import com.example.hf.models.Product;
import com.example.hf.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Optional;

public class ProductLocalDataSource implements ProductRepository {

  private static ArrayList<Product> srcProducts = new ArrayList<>();

  static{
    srcProducts.add(new Product(1, 1, "Wood Chair", R.drawable.f_1_1,
        new int[] {},25, "This is a beautiful chair"));
    srcProducts.add(new Product(2, 2, "Wood Table", R.drawable.f_2_1,
        new int[] {},100, "This is a wood table"));
//    srcProducts.add(new Product(3, 2, "Table A", 100, "Table A desc"));
//    srcProducts.add(new Product(5, 3, "Wallpaper A", 4, "WP A desc"));
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

//  public ArrayList<Product> getByCategory(final int categoryId) {
//    List<Product> outputProducts = (srcProducts.stream().parallel()
//        .filter(product -> product.getCategoryId() == categoryId)
//        .collect(Collectors.toList()));
//
//    return outputProducts;
//  }

}
