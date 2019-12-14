package com.example.hf.views.data;

/**
 * Product
 */
public class Product {

  private int id;
  private int categoryId;
  private String name;
  private int price;
  private String desc;

  public Product(int id, int categoryId, String name, int price, String desc) {
    this.id = id;
    this.categoryId = categoryId;
    this.name = name;
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

  public int getPrice() {
    return price;
  }

  public String getDesc() {
    return desc;
  }

}
