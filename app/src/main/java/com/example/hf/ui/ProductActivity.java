package com.example.hf.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hf.R;
import com.example.hf.models.Product;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.util.Constaint;

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
    ((ImageView) findViewById(R.id.iv_profile)).setImageResource(product.getProfileImage());
    ((TextView) findViewById(R.id.tv_name)).setText(product.getName());
    ((TextView) findViewById(R.id.tv_price)).setText(Constaint.CURRENCY + product.getPrice());
  }
}
