package com.example.hf.models;

/**
 * Product
 */
public class Product {

  private int id;
  private int categoryId;
  private String name;
  private String profileImage;
  private int price;
  private String desc;
  private Place place;
  private int arModelId;

  public Product(int id, int categoryId, String name, String profileImage, int price, String desc,
                 Place place, int arModelId) {
    this.id = id;
    this.categoryId = categoryId;
    this.name = name;
    this.profileImage = profileImage;
    this.price = price;
    this.desc = desc;
    this.place = place;
    this.arModelId = arModelId;
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

  public String getProfileImage() {
    return profileImage;
  }

  public int getPrice() {
    return price;
  }

  public String getDesc() {
    return desc;
  }

  public Place getPlace() {
    return place;
  }

  public int getArModelId() {
    return arModelId;
  }

}
