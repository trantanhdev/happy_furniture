package com.example.hf.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hf.R;

public class ProductActivity extends AppCompatActivity {

  private static final String TAG = "ProductActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);

    Bundle b = getIntent().getExtras();
    int value = -1; // or other values
    if(b != null)
      value = b.getInt("pid");

    Toast.makeText(this, "product Id:" + value, Toast.LENGTH_LONG).show();
  }
}
