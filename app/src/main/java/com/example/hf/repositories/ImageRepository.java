package com.example.hf.repositories;

import com.example.hf.views.data.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImageRepository {

  private static List<Image> srcImages = new ArrayList<>();

  static {

  }

  /**
   * get product images
   *
   * @param productId
   * @return
   */
  public static List<Image> getImages(int productId) {
    List<Image> products = srcImages.stream().parallel()
        .filter(image -> image.getProductId() == productId)
        .collect(Collectors.toList());

    return products;
  }

}
