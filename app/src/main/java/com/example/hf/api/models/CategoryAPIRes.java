package com.example.hf.api.models;

import com.google.gson.annotations.SerializedName;

public class CategoryAPIRes {

  @SerializedName("id")
  private Integer id;
  @SerializedName("name")
  private String name;

  public CategoryAPIRes(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
