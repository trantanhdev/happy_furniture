package com.example.hf.repositories.lds;

import com.example.hf.models.data.Product;
import com.example.hf.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductLocalDataSource implements ProductRepository {

  private static List<Product> srcProducts = new ArrayList<>();

  static{
    srcProducts.add(new Product(1, 1, "Chair A", 25, "Chair A desc"));
    srcProducts.add(new Product(2, 1, "Chair B", 27, "Chair B desc"));
    srcProducts.add(new Product(3, 2, "Table A", 100, "Table A desc"));
    srcProducts.add(new Product(4, 2, "Table B", 120, "Table B desc"));
    srcProducts.add(new Product(5, 3, "Wallpaper A", 4, "WP A desc"));
    srcProducts.add(new Product(6, 3, "Wallpaper B", 4, "WP B desc"));
  }

  public List<Product> get() {
    return srcProducts;
  }

  public List<Product> getByCategory(final int categoryId) {
    List<Product> outputProducts = (srcProducts.stream().parallel()
        .filter(product -> product.getCategoryId() == categoryId)
        .collect(Collectors.toList()));

    return outputProducts;
  }

}
