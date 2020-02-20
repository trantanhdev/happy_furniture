package com.example.hf.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hf.R;
import com.example.hf.models.data.Product;
import com.example.hf.repositories.ProductRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ProductActivity extends DaggerAppCompatActivity {

  private static final String TAG = "ProductActivity";

  @Inject
  ProductRepository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);

    Bundle b = getIntent().getExtras();
    int value = -1; // or other values
    if(b != null)
      value = b.getInt("pid");

    Product product = repository.get(value);
    //Toast.makeText(this, "product Id:" + product.getName(), Toast.LENGTH_LONG).show();

  }
}
