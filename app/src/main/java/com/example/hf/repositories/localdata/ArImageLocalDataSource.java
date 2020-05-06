package com.example.hf.repositories.localdata;

import com.example.hf.R;
import com.example.hf.models.ArImage;
import com.example.hf.repositories.ArImageRepository;

import java.util.ArrayList;
import java.util.List;

public class ArImageLocalDataSource implements ArImageRepository {

  private static ArrayList<ArImage> src = new ArrayList<>();

  static {
    src.add(new ArImage(1, "bonsai", R.drawable.bonsai, 4));
    src.add(new ArImage(2, "plant", R.drawable.plant, 5));
  }

  @Override
  public List<ArImage> get() {
    return src;
  }

}
