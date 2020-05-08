package com.example.hf.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * POJO class of Product response
 */
public class ProductAPIRes {

  @SerializedName("id")
  private Integer id;
  @SerializedName("categoryId")
  private Integer categoryId;
  @SerializedName("name")
  private String name;
  @SerializedName("profileImage")
  private String profileImage;
  @SerializedName("price")
  private Integer price;
  @SerializedName("desc")
  private String desc;
  @SerializedName("place")
  private String place;
  @SerializedName("arModelId")
  private Integer arModelId;

  public ProductAPIRes(Integer id, Integer categoryId, String name, String profileImage,
                       Integer price, String desc, String place, Integer arModelId) {
    this.id = id;
    this.categoryId = categoryId;
    this.name = name;
    this.profileImage = profileImage;
    this.price = price;
    this.desc = desc;
    this.place = place;
    this.arModelId = arModelId;
  }

  public Integer getId() {
    return id;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }

  public String getDesc() {
    return desc;
  }

  public String getPlace() {
    return place;
  }

  public Integer getArModelId() {
    return arModelId;
  }

}
