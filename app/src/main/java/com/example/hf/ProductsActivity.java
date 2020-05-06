package com.example.hf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hf.ProductActivity;
import com.example.hf.R;
import com.example.hf.models.Product;
import com.example.hf.repositories.ProductLocalDataSource;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.adapters.ProductsAdapter;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

  private static final String TAG = "ProductsActivity";
  private ProductRepository productRepository;
  private ListView lvProducts;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_products);

    productRepository = new ProductLocalDataSource();

    lvProducts = findViewById(R.id.lv_products);
    ArrayList<Product> products = productRepository.get();
    ProductsAdapter adapter = new ProductsAdapter(this, R.layout.lvr_product, products);
    lvProducts.setAdapter(adapter);
    lvProducts.setOnItemClickListener((parent, view, position, id) -> {
      Product selectedProduct = products.get(position);
      Intent intent = new Intent(this, ProductActivity.class);
      Bundle b = new Bundle();
      b.putInt("pid", selectedProduct.getId());
      intent.putExtras(b);
      startActivity(intent);
    });

  }
}
