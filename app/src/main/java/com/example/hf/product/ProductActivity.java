package com.example.hf.product;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hf.R;
import com.example.hf.models.Product;
import com.example.hf.repositories.localdata.ProductLocalDataSource;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.util.Constaint;

public class ProductActivity extends AppCompatActivity {

  private static final String TAG = "ProductActivity";

  private ProductRepository repository;
  private Button btnPlaceIt;
  private int pId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);
    repository = new ProductLocalDataSource();

    Bundle b = getIntent().getExtras();
    if(b != null)
      pId = b.getInt("pid");

    Product product = repository.get(pId);
    ((ImageView) findViewById(R.id.iv_profile)).setImageResource(product.getProfileImage());
    ((TextView) findViewById(R.id.tv_name)).setText(product.getName());
    ((TextView) findViewById(R.id.tv_price)).setText(Constaint.CURRENCY + product.getPrice());

    btnPlaceIt = findViewById(R.id.btn_place_id);
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
