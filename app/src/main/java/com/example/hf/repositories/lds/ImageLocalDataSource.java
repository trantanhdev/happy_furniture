package com.example.hf.repositories.lds;

import com.example.hf.models.data.Image;
import com.example.hf.repositories.ImageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImageLocalDataSource implements ImageRepository {

  private static List<Image> srcImages = new ArrayList<>();

  static {

  }

  /**
   * get product images
   *
   * @param productId
   * @return
   */
  public List<Image> getByProductId(int productId) {
    List<Image> products = srcImages.stream().parallel()
        .filter(image -> image.getProductId() == productId)
        .collect(Collectors.toList());

    return products;
  }

}
