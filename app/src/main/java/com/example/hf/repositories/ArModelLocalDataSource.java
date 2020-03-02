package com.example.hf.repositories;

import com.example.hf.models.ArModel;
import com.example.hf.models.ArPlaceType;
import com.example.hf.models.ArSourceType;

import java.util.ArrayList;
import java.util.Optional;

public class ArModelLocalDataSource implements ArModelRepository {

  private static ArrayList<ArModel> srcArModels = new ArrayList<>();

  static {
    srcArModels.add(new ArModel(1, ArPlaceType.FLOOR, ArSourceType.DEVICE, "Chair"));
  }

  @Override
  public ArModel get(int id) {
    Optional<ArModel> arModel = srcArModels.stream()
        .filter(p -> p.getId() == id)
        .findFirst();
    if (arModel.isPresent()) {
      return arModel.get();
    }

    return null;
  }
}
