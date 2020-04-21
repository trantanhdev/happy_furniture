package com.example.hf.repositories;

import com.example.hf.models.ArModel;
import com.example.hf.models.ArSourceType;

import java.util.ArrayList;
import java.util.Optional;

public class ArModelLocalDataSource implements ArModelRepository {

  private static ArrayList<ArModel> srcArModels = new ArrayList<>();

  static {
    srcArModels.add(new ArModel(1, ArSourceType.DEVICE, "Chair", 1));
    srcArModels.add(new ArModel(2, ArSourceType.DEVICE, "rug", 1));
    srcArModels.add(new ArModel(3, ArSourceType.INTERNET,
        "https://raw.githubusercontent.com/trantanhdev/data/master/desk.glb", 0.15f));
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
