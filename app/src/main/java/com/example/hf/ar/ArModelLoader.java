package com.example.hf.ar;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.hf.models.ArSourceType;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.concurrent.CompletableFuture;

/**
 * AR Model Loader
 */
public class ArModelLoader {

  /**
   * build 3D Model
   *
   * @param context
   * @param type DEVICE or INTERNET
   * @param url
   * @param scale
   * @return
   */
  public static CompletableFuture<ModelRenderable> buildModel(Context context, ArSourceType type,
                                                              String url, float scale) {

    if (type.equals(ArSourceType.DEVICE)) {

      // build model from Device
      return ModelRenderable.builder()
          .setSource(context, Uri.parse(url + ".sfb"))
          .build();
    } else if (type.equals(ArSourceType.INTERNET)) {

      // build model from Internet
      return ModelRenderable.builder()
          .setSource(context, RenderableSource.builder().setSource(
              context,
              Uri.parse(url),
              RenderableSource.SourceType.GLB)
          .setScale(scale)
          .setRecenterMode(RenderableSource.RecenterMode.ROOT)
          .build())
          .setRegistryId(url)
          .build();
    }

    Toast.makeText(context, "AR Model source type is not supported.", Toast.LENGTH_LONG).show();
    return CompletableFuture.completedFuture(null);
  }
}
