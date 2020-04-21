package com.example.hf.models;

// POJO class of Ar Model
public class ArModel {

  private int id;
  private ArSourceType sourceType;
  private String url;
  private float scale;

  public ArModel(int id, ArSourceType sourceType, String url, float scale) {
    this.id = id;
    this.sourceType = sourceType;
    this.url = url;
    this.scale = scale;
  }

  public int getId() {
    return id;
  }

  public ArSourceType getSourceType() {
    return sourceType;
  }

  public String getUrl() {
    return url;
  }

  public float getScale() {
    return scale;
  }
}
