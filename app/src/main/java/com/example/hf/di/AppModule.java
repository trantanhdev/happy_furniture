package com.example.hf.di;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  @Provides
  static String someString() {
    return "This is string";
  }
}
