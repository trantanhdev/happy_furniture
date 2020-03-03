package com.example.hf.ar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.hf.R;
import com.example.hf.models.ArSourceType;
import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Collection;

public class ArImageFragment extends ArFragment {

  private static final String TAG = ArImageFragment.class.getSimpleName();

  @Override
  protected Config getSessionConfiguration(Session session) {
    Config config = super.getSessionConfiguration(session);
    config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
    config.setFocusMode(Config.FocusMode.AUTO);
    loadImageDatabase(config, session);
    return config;
  }

  private void loadImageDatabase(Config config, Session session) {
    Bitmap bonsaiBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bonsai);
    Bitmap plantBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plant);
    AugmentedImageDatabase aid = new AugmentedImageDatabase(session);
    aid.addImage("bonsai", bonsaiBitmap);
    aid.addImage("plant", plantBitmap);
    config.setAugmentedImageDatabase(aid);
  }

  @Override
  public void onUpdate(FrameTime frameTime) {
    Frame frame = getArSceneView().getArFrame();
    Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);

    for (AugmentedImage image : images) {
      if (image.getTrackingState().equals(TrackingState.TRACKING)) {
        String imgName = image.getName();
        if ("bonsai".equals(imgName) || "plant".equals(imgName)) {
          Anchor anchor = image.createAnchor(image.getCenterPose());
          ArModelLoader.buildModel(getContext(), ArSourceType.DEVICE, image.getName(), 1)
              .thenAccept(builtModel -> placeModel(anchor, builtModel))
              .exceptionally(
                  throwable -> {
                    Log.e(TAG, "Unable to load Renderable.", throwable);
                    return null;
                  });
        }
      }
    }

    super.onUpdate(frameTime);
  }

  public void placeModel(Anchor anchor, ModelRenderable modelRenderable) {
    AnchorNode anchorNode = new AnchorNode(anchor);
    anchorNode.setRenderable(modelRenderable);
    getArSceneView().getScene().addChild(anchorNode);
  }

}
