package com.example.hf.ar;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.hf.models.ArSourceType;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.concurrent.CompletableFuture;

public class ArSourceBuilder {

  public static CompletableFuture<ModelRenderable> buildModel(Context context, ArSourceType type,
                                                              String url) {
    if (type.equals(ArSourceType.DEVICE)) {
      return ModelRenderable.builder()
          .setSource(context, Uri.parse(url + ".sfb"))
          .build();
    }

    Toast.makeText(context, "AR Model source type is not supported.", Toast.LENGTH_LONG).show();
    return CompletableFuture.completedFuture(null);
  }
}
