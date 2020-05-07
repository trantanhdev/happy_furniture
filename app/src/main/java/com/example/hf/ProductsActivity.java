package com.example.hf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hf.models.Product;
import com.example.hf.adapters.ProductsAdapter;
import com.example.hf.viewmodels.ProductsViewModel;

import java.util.List;


public class ProductsActivity extends AppCompatActivity {

  private static final String TAG = "ProductsActivity";
  private ProductsViewModel viewModel;
  private ListView lvProducts;
  private ProductsAdapter productsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_products);

    lvProducts = findViewById(R.id.lv_products);

    // init view model
    initViewModel();

    // init products list view
    initProductsListView();
  }

  /**
   * initialize view model
   */
  private void initViewModel() {
    viewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
    viewModel.init();
    viewModel.getProducts().observe(this, new Observer<List<Product>>() {
      @Override
      public void onChanged(List<Product> products) {
        productsAdapter.setNotifyOnChange(true);
      }
    });
  }

  /**
   * initialize Products list view
   */
  private void initProductsListView() {
    List<Product> products = viewModel.getProducts().getValue();
    productsAdapter = new ProductsAdapter(this, R.layout.lvr_product, products);
    lvProducts.setAdapter(productsAdapter);

    // set Product list view item on click listener
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
