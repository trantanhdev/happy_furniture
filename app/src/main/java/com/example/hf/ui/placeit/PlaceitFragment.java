package com.example.hf.ui.placeit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hf.R;

public class PlaceitFragment extends Fragment {

  private PlaceitViewModel placeitViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    placeitViewModel =
        ViewModelProviders.of(this).get(PlaceitViewModel.class);
    View root = inflater.inflate(R.layout.fragment_placeit, container, false);
    final TextView textView = root.findViewById(R.id.text_notifications);
    placeitViewModel.getText().observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
}