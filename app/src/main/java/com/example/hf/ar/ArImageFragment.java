package com.example.hf.ar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.hf.models.ArImage;
import com.example.hf.models.ArSourceType;
import com.example.hf.repositories.ArImageRepository;
import com.example.hf.repositories.localdata.ArImageLocalDataSource;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ARFragment for Image
 */
public class ArImageFragment extends ArFragment {

  private static final String TAG = ArImageFragment.class.getSimpleName();
  private static List<String> arImgNames = null;
  private static List<ArImage> arImages = null;
  private ArImageRepository repository;

  @Override
  protected Config getSessionConfiguration(Session session) {

    // initialize dependency
    repository = new ArImageLocalDataSource();

    // Custom Ar session config
    Config config = super.getSessionConfiguration(session);
    config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
    config.setFocusMode(Config.FocusMode.AUTO);

    // Add list of image to Ar session config
    AugmentedImageDatabase arImgDB = getAugImageDB(session);
    config.setAugmentedImageDatabase(arImgDB);

    // init list of Ar image names
    arImgNames = getAuImgNames();

    return config;
  }

  @Override
  public void onUpdate(FrameTime frameTime) {
    Frame frame = getArSceneView().getArFrame();

    // Get list of tracked images
    Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);

    for (AugmentedImage image : images) {
      if (image.getTrackingState().equals(TrackingState.TRACKING)) {
        String imgName = image.getName();

        // check if imgName exist in list of ar image names
        if (arImgNames.contains(imgName)) {

          // Create an anchor
          Anchor anchor = image.createAnchor(image.getCenterPose());

          // Load Ar model and render if success
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

  /**
   * get Augmented Image Database
   *
   * @param session
   * @return
   */
  private AugmentedImageDatabase getAugImageDB(Session session) {
    AugmentedImageDatabase aid = new AugmentedImageDatabase(session);
    List<ArImage> imgs = getArImages();

    if (imgs.isEmpty()) {
      return aid;
    }

    imgs.stream().forEach((img) -> {
      Bitmap bitmap = BitmapFactory.decodeResource(getResources(), img.getImageResId());
      aid.addImage(img.getName(), bitmap);
    });

    return aid;
  }

  /**
   * get list of ArImage
   *
   * @return
   */
  private List<ArImage> getArImages() {
    if (arImages != null) {
      return arImages;
    }

    arImages = repository.get();
    return arImages;
  }

  /**
   * get Augmented Image names
   *
   * @return
   */
  private List<String> getAuImgNames() {
    List<String> oArImageNames = new ArrayList<>();
    List<ArImage> imgs = getArImages();

    if (imgs.isEmpty()) {
      return oArImageNames;
    }

    imgs.stream().forEach((img) -> {
      oArImageNames.add(img.getName());
    });

    return oArImageNames;
  }

  /**
   * place ArModel at anchor
   *
   * @param anchor
   * @param modelRenderable
   */
  private void placeModel(Anchor anchor, ModelRenderable modelRenderable) {
    AnchorNode anchorNode = new AnchorNode(anchor);
    anchorNode.setRenderable(modelRenderable);
    getArSceneView().getScene().addChild(anchorNode);
  }

}
