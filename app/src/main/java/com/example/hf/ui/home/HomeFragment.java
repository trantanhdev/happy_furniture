package com.example.hf.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hf.R;
import com.example.hf.models.data.Product;
import com.example.hf.repositories.ProductRepository;
import com.example.hf.repositories.lds.ProductLocalDataSource;
import com.example.hf.ui.adapters.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

  private static final String TAG = "HomeFragment";
  private HomeViewModel homeViewModel;
  private ProductRepository productRepository;
  private ListView lstProducts;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
        ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    productRepository = new ProductLocalDataSource();

//    final TextView textView = root.findViewById(R.id.text_home);
//    homeViewModel.getText().observe(this, new Observer<String>() {
//      @Override
//      public void onChanged(@Nullable String s) {
//        textView.setText(s);
//      }
//    });
    lstProducts = root.findViewById(R.id.lst_products);
    ArrayList<Product> products = productRepository.get();
    ProductsAdapter adapter = new ProductsAdapter(getContext(), R.layout.row_product, products);
    lstProducts.setAdapter(adapter);
    return root;
  }
}