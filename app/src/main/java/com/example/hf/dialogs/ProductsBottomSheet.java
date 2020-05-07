package com.example.hf.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hf.R;
import com.example.hf.adapters.ProductsRecycleViewAdapter;
import com.example.hf.models.Product;
import com.example.hf.viewmodels.ProductsBottomSheetViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class ProductsBottomSheet extends BottomSheetDialogFragment {

  private static final String TAG = "ProductsBottomSheet";
  private RecyclerView rcvProducts;
  private ProductsRecycleViewAdapter rcvaProducts;
  private ProductsBottomSheetViewModel viewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = getLayoutInflater().inflate(R.layout.bs_products, container, false);

    rcvProducts = v.findViewById(R.id.rcv_products);

    // initialize view model
    initViewModel();

    // initialize Products card recycle view
    initProductCardsRecycleView();

    return v;
  }

  /**
   * initialize view model
   */
  private void initViewModel() {
    viewModel = ViewModelProviders.of(this).get(ProductsBottomSheetViewModel.class);
    viewModel.init();

    // add products list observer
    viewModel.getProducts().observe(this, new Observer<List<Product>>() {
      @Override
      public void onChanged(List<Product> products) {
        rcvaProducts.notifyDataSetChanged();
      }
    });
  }

  /**
   * initialize product cards recycle view
   */
  private void initProductCardsRecycleView() {
    rcvaProducts = new ProductsRecycleViewAdapter(getContext(),
        viewModel.getProducts().getValue());
    rcvProducts.setLayoutManager(new LinearLayoutManager(getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    rcvProducts.setAdapter(rcvaProducts);
  }

}
