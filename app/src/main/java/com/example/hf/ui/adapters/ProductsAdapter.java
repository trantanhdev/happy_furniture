package com.example.hf.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hf.R;
import com.example.hf.models.Product;
import com.example.hf.util.Constaint;

import java.util.ArrayList;

public class ProductsAdapter extends ArrayAdapter<Product> {

  private static final String TAG = "ProductsAdapter";
  private final Context mContext;

  public ProductsAdapter(@NonNull Context context, int resource, ArrayList<Product> products) {
    super(context, resource, products);

    this.mContext = context;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View rowView = inflater.inflate(R.layout.row_product, parent, false);


    Product product = getItem(position);
    String name = product.getName();
    int profileImage = product.getProfileImage();
    String priceStr = Constaint.CURRENCY + product.getPrice();
    ((ImageView)rowView.findViewById(R.id.img_profile)).setImageResource(profileImage);
    ((TextView)rowView.findViewById(R.id.name)).setText(name);
    ((TextView)rowView.findViewById(R.id.price)).setText(priceStr);
    return rowView;
  }
}
