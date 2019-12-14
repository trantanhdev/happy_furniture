package com.example.hf.views.data;

/**
 * Product image
 */
public class Image {

  private int id;
  private int productId;
  private String path;

  public Image(int id, int productId, String path) {
    this.id = id;
    this.productId = productId;
    this.path = path;
  }

  public int getId() {
    return id;
  }

  public int getProductId() {
    return productId;
  }

  public String getPath() {
    return path;
  }

}
