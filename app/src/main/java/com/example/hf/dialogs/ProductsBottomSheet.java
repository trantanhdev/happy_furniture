package com.example.hf.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hf.R;
import com.example.hf.adapters.ProductsRecycleViewAdapter;
import com.example.hf.models.Place;
import com.example.hf.models.Product;
import com.example.hf.repositories.localdata.ProductLocalDataSource;
import com.example.hf.repositories.ProductRepository;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class ProductsBottomSheet extends BottomSheetDialogFragment {

  private static final String TAG = "ProductsBottomSheet";
  private ProductRepository productRepository;
  private RecyclerView rcvProducts;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    productRepository = new ProductLocalDataSource();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = getLayoutInflater().inflate(R.layout.bs_products, container, false);

    rcvProducts = v.findViewById(R.id.rcv_products);
    rcvProducts.setLayoutManager(new LinearLayoutManager(getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    ArrayList<Product> products = (ArrayList) productRepository.getByPlace(Place.FLOOR);
    ProductsRecycleViewAdapter adapter = new ProductsRecycleViewAdapter(getContext(), products);
    rcvProducts.setAdapter(adapter);

    return v;
  }
}
