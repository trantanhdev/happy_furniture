package com.example.hf.repositories;

import com.example.hf.models.data.Image;

import java.util.List;

public interface ImageRepository {

  public List<Image> getByProductId(int productId);
}
