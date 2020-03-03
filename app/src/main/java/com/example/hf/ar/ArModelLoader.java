package com.example.hf.ar;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.hf.models.ArSourceType;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.concurrent.CompletableFuture;

public class ArModelLoader {

  public static CompletableFuture<ModelRenderable> buildModel(Context context, ArSourceType type,
                                                              String url) {
    if (type.equals(ArSourceType.DEVICE)) {
      return ModelRenderable.builder()
          .setSource(context, Uri.parse(url + ".sfb"))
          .build();
    } else if (type.equals(ArSourceType.INTERNET)) {
      return ModelRenderable.builder()
          .setSource(context, RenderableSource.builder().setSource(
              context,
              Uri.parse(url),
              RenderableSource.SourceType.GLB)
          .setScale(0.09f)  // Scale the original model to 50%.
          .setRecenterMode(RenderableSource.RecenterMode.ROOT)
          .build())
          .setRegistryId(url)
          .build();
    }

    Toast.makeText(context, "AR Model source type is not supported.", Toast.LENGTH_LONG).show();
    return CompletableFuture.completedFuture(null);
  }
}
