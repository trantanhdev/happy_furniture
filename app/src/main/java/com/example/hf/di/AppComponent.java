package com.example.hf.di;

import android.app.Application;

import com.example.hf.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
    modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class
    }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

  @Component.Builder
  interface Builder{

    @BindsInstance
    Builder app(Application app);

    AppComponent build();
  }

}
