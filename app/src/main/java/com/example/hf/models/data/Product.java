package com.example.hf.models.data;

/**
 * Product
 */
public class Product {

  private int id;
  private int categoryId;
  private String name;
  private int profileImage;
  private int[] images;
  private int price;
  private String desc;

  public Product(int id, int categoryId, String name, int profileImage, int[] images, int price,
                 String desc) {
    this.id = id;
    this.categoryId = categoryId;
    this.name = name;
    this.profileImage = profileImage;
    this.images = images;
    this.price = price;
    this.desc = desc;
  }

  public int getId() {
    return id;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public String getName() {
    return name;
  }

  public int getProfileImage() {
    return profileImage;
  }

  public int[] getImages() {
    return images;
  }

  public int getPrice() {
    return price;
  }

  public String getDesc() {
    return desc;
  }

}
