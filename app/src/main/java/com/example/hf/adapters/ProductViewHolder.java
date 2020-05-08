package com.example.hf.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hf.R;
import com.google.android.material.card.MaterialCardView;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  public ImageView productImage;
  public TextView productName;
  public TextView productPrice;
  ProductItemClickListener itemClickListener;

  public ProductViewHolder(@NonNull MaterialCardView itemView) {
    super(itemView);

    productImage = itemView.findViewById(R.id.product_image);
    productName = itemView.findViewById(R.id.product_name);
    productPrice = itemView.findViewById(R.id.product_price);

    itemView.setOnClickListener((View.OnClickListener) this);
  }

  @Override
  public void onClick(View v) {
    this.itemClickListener.onItemClick(v, getLayoutPosition());
  }

  public void setOnItemClickListener(ProductItemClickListener productItemClickListener) {
    this.itemClickListener = productItemClickListener;
  }

}
