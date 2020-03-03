package com.example.hf.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.hf.R;
import com.example.hf.ar.ArModelLoader;
import com.example.hf.ar.FloorArFragment;
import com.example.hf.ar.WallArFragment;
import com.example.hf.models.ArModel;
import com.example.hf.models.ArPlaceType;
import com.example.hf.repositories.ArModelLocalDataSource;
import com.example.hf.repositories.ArModelRepository;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PlaceItActivity extends AppCompatActivity {

  private static final String TAG = PlaceItActivity.class.getSimpleName();
  private static final double MIN_OPENGL_VERSION = 3.0;

  private ArModelRepository arModelRepository;
  private int arModelId;
  private ArFragment arFragment;
  private ModelRenderable mRenderable;
  private static FragmentManager fragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_place_it);

    if (!checkIsSupportedDeviceOrFinish(this)) {
      return;
    }
    arModelRepository = new ArModelLocalDataSource();

    Bundle b = getIntent().getExtras();
    if(b != null) arModelId = b.getInt("id");

    ArModel model = arModelRepository.get(arModelId);
    CompletableFuture<ModelRenderable> cfModelRenderable = ArModelLoader
        .buildModel(this, model.getSourceType(), model.getUrl());

    cfModelRenderable.thenAccept(result -> mRenderable = result)
        .exceptionally(
            throwable -> {
              Log.e(TAG, "Unable to load Renderable.", throwable);
              return null;
            });

    fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    ArPlaceType placeType = model.getType();
    if (ArPlaceType.FLOOR.equals(placeType)) {
      arFragment = new FloorArFragment();
    } else if (ArPlaceType.WALL.equals(placeType)) {
      arFragment = new WallArFragment();
    }
    fragmentTransaction.add(R.id.fragment_container, arFragment, null);
    fragmentTransaction.commit();

    arFragment.setOnTapArPlaneListener(
        (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
          Scene scene = arFragment.getArSceneView().getScene();
          if (mRenderable == null || hasAnchorNode(scene)) {
            return;
          }

          // Create the Anchor.
          Anchor anchor = hitResult.createAnchor();
          AnchorNode anchorNode = new AnchorNode(anchor);
          anchorNode.setParent(scene);

          // Create the transformable andy and add it to the anchor.
          TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
          andy.setParent(anchorNode);
          andy.setRenderable(mRenderable);
          andy.getScaleController().setSensitivity(0);
          andy.select();
        });
  }

  /**
   * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
   * on this device.
   *
   * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
   *
   * <p>Finishes the activity if Sceneform can not run
   */
  public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
      Log.e(TAG, "Sceneform requires Android N or later");
      Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
      activity.finish();
      return false;
    }
    String openGlVersionString =
        ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
            .getDeviceConfigurationInfo()
            .getGlEsVersion();
    if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
      Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
      Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
          .show();
      activity.finish();
      return false;
    }
    return true;
  }

  /**
   * Check whether scene have anchornode
   *
   * @param scene
   * @return
   */
  public boolean hasAnchorNode(Scene scene) {
    List<Node> nodes = scene.getChildren();

    if (nodes.isEmpty()) {
      return false;
    }

    for (Node node : nodes) {
      if (node instanceof AnchorNode) {
        return true;
      }
    }

    return false;
  }

}
