package com.example.hf.repositories.lds;

import com.example.hf.R;
import com.example.hf.models.data.Product;
import com.example.hf.repositories.ProductRepository;

import java.util.ArrayList;

public class ProductLocalDataSource implements ProductRepository {

  private static ArrayList<Product> srcProducts = new ArrayList<>();

  static{
    srcProducts.add(new Product(1, 1, "Wood Chair", R.drawable.f_1_1,
        new int[]{R.drawable.f_1_2, R.drawable.f_1_3},25, "This is a beautiful chair"));
//    srcProducts.add(new Product(3, 2, "Table A", 100, "Table A desc"));
//    srcProducts.add(new Product(5, 3, "Wallpaper A", 4, "WP A desc"));
  }

  public ArrayList<Product> get() {
    return srcProducts;
  }

//  public ArrayList<Product> getByCategory(final int categoryId) {
//    List<Product> outputProducts = (srcProducts.stream().parallel()
//        .filter(product -> product.getCategoryId() == categoryId)
//        .collect(Collectors.toList()));
//
//    return outputProducts;
//  }

}
