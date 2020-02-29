package com.example.hf.ar;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

public class FloorArFragment extends ArFragment {
  @Override
  protected Config getSessionConfiguration(Session session) {
    Config config = super.getSessionConfiguration(session);
    config.setPlaneFindingMode(Config.PlaneFindingMode.HORIZONTAL);
    return config;
  }
}
