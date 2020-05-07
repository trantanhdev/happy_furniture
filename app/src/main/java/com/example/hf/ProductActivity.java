package com.example.hf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hf.models.Product;
import com.example.hf.util.Constaint;
import com.example.hf.viewmodels.ProductViewModel;

public class ProductActivity extends AppCompatActivity {

  private static final String TAG = "ProductActivity";

  private ImageView ivProfileImage;
  private TextView tvName;
  private TextView tvPrice;
  private Button btnPlaceIt;
  private ProductViewModel viewModel;
  private int pId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);

    Bundle b = getIntent().getExtras();
    if(b != null)
      pId = b.getInt("pid");

    ivProfileImage = findViewById(R.id.iv_profile);
    tvName = findViewById(R.id.tv_name);
    tvPrice = findViewById(R.id.tv_price);

    // init view model
    initViewModel(pId);

    // init Place It button
    initPlaceItButton();
  }

  /**
   * initialize view model
   *
   * @param productId
   */
  private void initViewModel(int productId) {
    viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
    viewModel.init(productId);
    viewModel.getProduct().observe(this, new Observer<Product>() {
      @Override
      public void onChanged(Product product) {
        ivProfileImage.setImageResource(product.getProfileImage());
        tvName.setText(product.getName());
        tvPrice.setText(Constaint.CURRENCY + product.getPrice());
      }
    });
  }

  /**
   * initialize Place It button
   */
  private void initPlaceItButton() {
    btnPlaceIt = findViewById(R.id.btn_place_id);
    Product product = viewModel.getProduct().getValue();
    btnPlaceIt.setOnClickListener(v -> {
      int arModelId = product.getArModelId();
      if (arModelId > 0) {
        Intent intent = new Intent(this, PlaceItActivity.class);
        Bundle svaBundel = new Bundle();
        svaBundel.putInt("id", arModelId);
        svaBundel.putString("place", product.getPlace().name());
        intent.putExtras(svaBundel);
        startActivity(intent);
      }
    });
  }

}
