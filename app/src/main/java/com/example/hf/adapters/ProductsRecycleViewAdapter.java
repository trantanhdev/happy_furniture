package com.example.hf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hf.R;
import com.example.hf.dialogs.ProductBottomSheetItemClickListener;
import com.example.hf.models.Product;
import com.example.hf.util.Constaint;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductsRecycleViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {

  private static final String TAG = "ProductsRecycleViewAdapter";
  private Context c;
  private List<Product> models;
  private ProductBottomSheetItemClickListener itemClickListener;

  public ProductsRecycleViewAdapter(Context context, List<Product> products) {
    c = context;
    models = products;
    itemClickListener = (ProductBottomSheetItemClickListener) context;
  }

  public void setData(List<Product> products) {
    this.models = products;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    // inflate row card layout
    MaterialCardView v = (MaterialCardView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.lvr_card_product, parent,false);

    ProductViewHolder vh = new ProductViewHolder(v);
    return vh;
  }

  @Override
  public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    Product product = models.get(position);
    Picasso.with(c).load(product.getProfileImage()).into(holder.productImage);
    holder.productName.setText(product.getName());
    String priceStr = Constaint.CURRENCY + product.getPrice();
    holder.productPrice.setText(priceStr);

    holder.setOnItemClickListener((View v, int ip) -> {
      Product clickedProduct = models.get(ip);
      itemClickListener.onProductBottomSheetItemClick(clickedProduct);
    });
  }

  @Override
  public int getItemCount() {
    if (null == models) {
      return 0;
    }
    return models.size();
  }
}
