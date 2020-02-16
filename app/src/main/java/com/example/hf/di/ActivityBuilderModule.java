package com.example.hf.di;

import com.example.hf.di.account.AccountModule;
import com.example.hf.ui.IndexActivity;
import com.example.hf.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

  @ContributesAndroidInjector
  abstract MainActivity contributeMainActivity();

  @ContributesAndroidInjector (
      modules = {
          AccountModule.class
      }
  )
  abstract IndexActivity contributeIndexActivity();
}
