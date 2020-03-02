package com.example.hf.ui.scanit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScanitViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public ScanitViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is notifications fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}