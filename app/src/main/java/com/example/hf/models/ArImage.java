package com.example.hf.models;

/**
 * POJO class for AR image
 */
public class ArImage {

  private int id;
  private String name;
  private int imageResId;
  private int arModelId;

  public ArImage(int id, String name, int imageResId, int arModelId) {
    this.id = id;
    this.name = name;
    this.imageResId = imageResId;
    this.arModelId = arModelId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getImageResId() {
    return imageResId;
  }

  public int getArModelId() {
    return arModelId;
  }

}
