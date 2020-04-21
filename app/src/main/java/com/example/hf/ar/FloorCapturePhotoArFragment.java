package com.example.hf.ar;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Environment;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Combine plane AR Fragment
 */
public class FloorCapturePhotoArFragment extends ArFragment {

  @Override
  protected Config getSessionConfiguration(Session session) {
    Config config = super.getSessionConfiguration(session);
    config.setPlaneFindingMode(Config.PlaneFindingMode.HORIZONTAL);
    return config;
  }

  /**
   * add permission to write external storage
   *
   * @return
   */
  @Override
  public String[] getAdditionalPermissions() {
    String[] additionalPermissions = super.getAdditionalPermissions();
    int permissionLength = additionalPermissions != null ? additionalPermissions.length : 0;
    String[] permissions = new String[permissionLength + 1];
    permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    if (permissionLength > 0) {
      System.arraycopy(additionalPermissions, 0, permissions, 1, additionalPermissions.length);
    }
    return permissions;
  }

}