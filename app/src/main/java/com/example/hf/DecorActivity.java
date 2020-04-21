package com.example.hf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hf.ar.ArModelLoader;
import com.example.hf.dialogs.NavBottomSheet;
import com.example.hf.dialogs.ProductBottomSheetItemClickListener;
import com.example.hf.dialogs.ProductsBottomSheet;
import com.example.hf.models.ArModel;
import com.example.hf.models.Product;
import com.example.hf.product.ProductsActivity;
import com.example.hf.repositories.ArModelLocalDataSource;
import com.example.hf.repositories.ArModelRepository;
import com.example.hf.repositories.ProductRepository;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DecorActivity extends AppCompatActivity implements
    NavBottomSheet.NavBottomSheetListener, ProductBottomSheetItemClickListener {

  private static final String TAG = "Decor Activity";
  private BottomAppBar babDecor;
  private FloatingActionButton fabAdd;

//  private LinearLayout bsNav;
//  private BottomSheetBehavior bsbNav;
//  private LinearLayout bsNavHeader;
//  private LinearLayout bsNavList;
//  private LinearLayout bsNavAri;

  private ProductsBottomSheet bsProducts;
  private BottomSheetBehavior bsbProducts;
  private LinearLayout bsProductsHeader;
  private ListView lvProducts;

  private ProductRepository repository;
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

//    bsNav = findViewById(R.id.bs_nav);
//    bsbNav = BottomSheetBehavior.from(bsNav);
//    bsbNav.setState(BottomSheetBehavior.STATE_HIDDEN);
//    bsNavHeader = findViewById(R.id.bs_nav_header);
//    bsNavList = findViewById(R.id.bs_nav_list);
//    bsNavAri = findViewById(R.id.bs_nav_ari);

//    bsProducts = findViewById(R.id.bs_products);
//    bsbProducts = BottomSheetBehavior.from(bsProducts);
//    bsbProducts.setState(BottomSheetBehavior.STATE_HIDDEN);
//    bsProductsHeader = findViewById(R.id.bs_products_header);
//    lvProducts = findViewById(R.id.lv_products);

//    repository = new ProductLocalDataSource();
//    arModelRepository = new ArModelLocalDataSource();

    fragment = (ArFragment)
        getSupportFragmentManager().findFragmentById(R.id.arf_decor);
    fragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
      fragment.onUpdate(frameTime);
      arFragmentUpdated();
    });



//    bsbNav.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//      @Override
//      public void onStateChanged(@NonNull View view, int i) {
//        if (BottomSheetBehavior.STATE_HIDDEN == i) {
//          fabAdd.setVisibility(View.VISIBLE);
//        }
//      }
//
//      @Override
//      public void onSlide(@NonNull View view, float v) {
//
//      }
//    });
//
//    bsNavHeader.setOnClickListener(e -> {
//      bsbNav.setState(BottomSheetBehavior.STATE_HIDDEN);
//    });
//    bsbProducts.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//      @Override
//      public void onStateChanged(@NonNull View view, int i) {
//        if (BottomSheetBehavior.STATE_HIDDEN == i) {
//          fabAdd.setVisibility(View.VISIBLE);
//        }
//      }
//
//      @Override
//      public void onSlide(@NonNull View view, float v) {
//
//      }
//    });
//
//    bsProductsHeader.setOnClickListener(e -> {
//      bsbProducts.setState(BottomSheetBehavior.STATE_HIDDEN);
//    });
//
//    fabAdd.setOnClickListener(e -> {
//      fabAdd.setVisibility(View.INVISIBLE);
//      bsbProducts.setState(BottomSheetBehavior.STATE_EXPANDED);
//    });
//
//    lvProducts = findViewById(R.id.lv_products);
//    ArrayList<Product> products = (ArrayList<Product>) repository.getByPlace(Place.FLOOR);
//    ProductsAdapter adapter = new ProductsAdapter(this, R.layout.lvr_product, products);
//    lvProducts.setAdapter(adapter);
//    lvProducts.setOnItemClickListener((parent, view, position, id) -> {
//      Product selectedProduct = products.get(position);
//      ArModel model = arModelRepository.get(selectedProduct.getArModelId());
//      CompletableFuture<ModelRenderable> cfModelRenderable = ArModelLoader
//          .buildModel(this, model.getSourceType(), model.getUrl(), model.getScale());
//
//      cfModelRenderable.thenAccept(result -> {
//        mRenderable = result;
//        Toast.makeText(this, "The product is ready.", Toast.LENGTH_LONG).show();
//      })
//      .exceptionally(throwable -> {
//        Toast.makeText(this, "The product is not ready.", Toast.LENGTH_LONG).show();
//        return null;
//      });
//    });


  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.mi_photo: {
        Toast.makeText(this, "Take photo", Toast.LENGTH_LONG).show();
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

  private android.graphics.Point getScreenCenter() {
    View vw = findViewById(android.R.id.content);
    return new android.graphics.Point(vw.getWidth()/2, vw.getHeight()/2);
  }

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
}
