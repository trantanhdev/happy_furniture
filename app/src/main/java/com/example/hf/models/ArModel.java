package com.example.hf.models;

// POJO class of Ar Model
public class ArModel {

  private int id;
  private ArPlaceType type;
  private ArSourceType sourceType;
  private String url;

  public ArModel(int id, ArPlaceType type, ArSourceType sourceType, String url) {
    this.id = id;
    this.type = type;
    this.sourceType = sourceType;
    this.url = url;
  }

  public int getId() {
    return id;
  }

  public ArPlaceType getType() {
    return type;
  }

  public ArSourceType getSourceType() {
    return sourceType;
  }

  public String getUrl() {
    return url;
  }
}
