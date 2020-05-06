package com.example.hf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Menu;
import android.view.MenuItem;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.hf.ar.ArModelLoader;
import com.example.hf.dialogs.NavBottomSheet;
import com.example.hf.dialogs.ProductBottomSheetItemClickListener;
import com.example.hf.dialogs.ProductsBottomSheet;
import com.example.hf.models.ArModel;
import com.example.hf.models.Product;
import com.example.hf.repositories.ArModelLocalDataSource;
import com.example.hf.repositories.ArModelRepository;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DecorActivity extends AppCompatActivity implements
    NavBottomSheet.NavBottomSheetListener, ProductBottomSheetItemClickListener {

  private static final String TAG = "Decor Activity";
  private BottomAppBar babDecor;
  private FloatingActionButton fabAdd;
  private ProductsBottomSheet bsProducts;
  private ArModelRepository arModelRepository;
  private ModelRenderable mRenderable;

  private ArFragment fragment;

  @SuppressLint("RestrictedApi")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_decor);

    // initialize repository
    arModelRepository = new ArModelLocalDataSource();

    babDecor = findViewById(R.id.bab_decor);
    setSupportActionBar(babDecor);
    fabAdd = findViewById(R.id.fab_add);

    babDecor.setNavigationOnClickListener(e -> {
      NavBottomSheet bsNav = new NavBottomSheet();
      bsNav.show(getSupportFragmentManager(), "navBottomSheet");
    });

    fabAdd.setOnClickListener((View view) -> {
      bsProducts = new ProductsBottomSheet();
      bsProducts.show(getSupportFragmentManager(), "productsBottomSheet");
    });

    fragment = (ArFragment)
        getSupportFragmentManager().findFragmentById(R.id.arf_decor);
    fragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
      fragment.onUpdate(frameTime);
      arFragmentUpdated();
    });

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.mi_photo: {
        takePhoto();
        break;
      }
    }
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.bab_decor_nav, menu);
    return true;
  }

  /**
   * arFragment update
   */
  private void arFragmentUpdated() {
    if (null != mRenderable) {
      Frame frame = fragment.getArSceneView().getArFrame();
      android.graphics.Point pt = getScreenCenter();
      List<HitResult> hits;
      if (frame != null) {
        hits = frame.hitTest(pt.x, pt.y);
        for (HitResult hit : hits) {
          Trackable trackable = hit.getTrackable();
          if (trackable instanceof Plane &&
              ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
            Anchor anchor = hit.createAnchor();
            addNodeToScene(anchor, mRenderable);
          }
        }
      }
    }
  }

  /**
   * get screen center
   *
   * @return
   */
  private android.graphics.Point getScreenCenter() {
    View vw = findViewById(android.R.id.content);
    return new android.graphics.Point(vw.getWidth()/2, vw.getHeight()/2);
  }

  /**
   * add Node to Scene
   *
   * @param anchor
   * @param renderable
   */
  public void addNodeToScene(Anchor anchor, ModelRenderable renderable) {
    AnchorNode anchorNode = new AnchorNode(anchor);
    TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
    node.setRenderable(renderable);
    node.setParent(anchorNode);
    fragment.getArSceneView().getScene().addChild(anchorNode);
    mRenderable = null;
  }

  @Override
  public void onNavBottomSheetItemClicked(int itemId) {
    if (NavBottomSheet.LIST_VIEW == itemId) {
      Intent intent = new Intent(this, ProductsActivity.class);
      startActivity(intent);
      return;
    }

    if (NavBottomSheet.SCAN_IMAGE == itemId) {
      Intent intent = new Intent(this, ScanImageActivity.class);
      startActivity(intent);
      return;
    }
  }

  @Override
  public void onProductBottomSheetItemClick(Product product) {
    ArModel model = arModelRepository.get(product.getArModelId());
    CompletableFuture<ModelRenderable> cfModelRenderable = ArModelLoader
        .buildModel(this, model.getSourceType(), model.getUrl(), model.getScale());

    cfModelRenderable.thenAccept(result -> {
      mRenderable = result;
      Toast.makeText(this, "The product is ready.", Toast.LENGTH_LONG).show();
    })
    .exceptionally(throwable -> {
      Toast.makeText(this, "The product is not ready.", Toast.LENGTH_LONG).show();
      return null;
    });

    bsProducts.dismiss();
  }

  /**
   * take a photo
   */
  private void takePhoto() {
    final String filename = generateFilename();
    ArSceneView view = fragment.getArSceneView();

    // Create a bitmap the size of the scene view.
    final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
        Bitmap.Config.ARGB_8888);

    // Create a handler thread to offload the processing of the image.
    final HandlerThread handlerThread = new HandlerThread("PixelCopier");
    handlerThread.start();
    // Make the request to copy.
    PixelCopy.request(view, bitmap, (copyResult) -> {
      if (copyResult == PixelCopy.SUCCESS) {
        try {
          saveBitmapToDisk(bitmap, filename);
        } catch (IOException e) {
          Toast toast = Toast.makeText(DecorActivity.this, e.toString(),
              Toast.LENGTH_LONG);
          toast.show();
          return;
        }
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
            "Photo saved", Snackbar.LENGTH_LONG);
        snackbar.setAction("Open in Photos", v -> {
          File photoFile = new File(filename);

          Uri photoURI = FileProvider.getUriForFile(DecorActivity.this,
              DecorActivity.this.getPackageName() + ".ar.name.provider",
              photoFile);
          Intent intent = new Intent(Intent.ACTION_VIEW, photoURI);
          intent.setDataAndType(photoURI, "image/*");
          intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
          startActivity(intent);

        });
        snackbar.show();
      } else {
        Toast toast = Toast.makeText(DecorActivity.this,
            "Failed to copyPixels: " + copyResult, Toast.LENGTH_LONG);
        toast.show();
      }
      handlerThread.quitSafely();
    }, new Handler(handlerThread.getLooper()));
  }

  /**
   * generate Filename
   *
   * @return
   */
  private String generateFilename() {
    String date =
        new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
    return Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES) + File.separator + "HappyFurniture/" + date + "_screenshot.jpg";
  }

  private void saveBitmapToDisk(Bitmap bitmap, String filename) throws IOException {

    File out = new File(filename);
    if (!out.getParentFile().exists()) {
      boolean rs = out.getParentFile().mkdirs();
      if (!rs) {
        throw new IOException("Failed to to create app folder");
      }
    }
    try (FileOutputStream outputStream = new FileOutputStream(filename);
         ByteArrayOutputStream outputData = new ByteArrayOutputStream()) {
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputData);
      outputData.writeTo(outputStream);
      outputStream.flush();
      outputStream.close();
    } catch (IOException ex) {
      throw new IOException("Failed to save bitmap to disk", ex);
    }
  }

}
