package com.example.hf.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hf.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NavBottomSheet extends BottomSheetDialogFragment {
  public static final int LIST_VIEW = 1;
  public static final int SCAN_IMAGE = 2;
  private LinearLayout btnListView;
  private LinearLayout btnArImage;
  private NavBottomSheetListener mListener;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = getLayoutInflater().inflate(R.layout.bs_nav, container, false);

    btnListView = v.findViewById(R.id.bs_nav_list);
    btnArImage =  v.findViewById(R.id.bs_nav_ari);

    btnListView.setOnClickListener((View view) -> {
      mListener.onNavBottomSheetItemClicked(LIST_VIEW);
      dismiss();
    });

    btnArImage.setOnClickListener((View view) -> {
      mListener.onNavBottomSheetItemClicked(SCAN_IMAGE);
      dismiss();
    });

    return v;
  }

  /**
   * Nav bottom sheet listener
   */
  public interface NavBottomSheetListener {
    void onNavBottomSheetItemClicked(int itemId);
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);

    // bind Activity as NavBottomSheetListener
    try {
      mListener = (NavBottomSheetListener) context;
    } catch (ClassCastException ex) {
      throw new ClassCastException(context.toString() + " must implement navBottomSheetListener");
    }
  }
}
