package com.example.hf.repositories.localdata;

import com.example.hf.models.ArModel;
import com.example.hf.models.ArSourceType;
import com.example.hf.repositories.ArModelRepository;

import java.util.ArrayList;
import java.util.Optional;

public class ArModelLocalDataSource implements ArModelRepository {

  private static ArrayList<ArModel> srcArModels = new ArrayList<>();

  static {
    srcArModels.add(new ArModel(1, ArSourceType.DEVICE, "Chair", 1));
    srcArModels.add(new ArModel(2, ArSourceType.DEVICE, "rug", 1));
    srcArModels.add(new ArModel(3, ArSourceType.INTERNET,
        "https://raw.githubusercontent.com/trantanhdev/data/master/desk.glb", 0.15f));
    srcArModels.add(new ArModel(4, ArSourceType.DEVICE, "bonsai", 1));
    srcArModels.add(new ArModel(5, ArSourceType.DEVICE, "plant", 1));
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
