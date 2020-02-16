package com.example.hf.ui.placeit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaceitViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public PlaceitViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is notifications fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}