package com.example.hf.ar;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

/**
 * AR Fragment for Wall
 */
public class WallArFragment extends ArFragment {

  @Override
  protected Config getSessionConfiguration(Session session) {
    Config config = super.getSessionConfiguration(session);
    config.setPlaneFindingMode(Config.PlaneFindingMode.VERTICAL);
    return config;
  }
}