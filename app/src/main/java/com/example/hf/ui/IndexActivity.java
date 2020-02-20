package com.example.hf.ui;

import android.os.Bundle;

import com.example.hf.R;
import com.example.hf.repositories.AccountRespository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class IndexActivity extends DaggerAppCompatActivity {

  private static final String TAG = "Index Activity";

  @Inject
  AccountRespository respository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_index);
    BottomNavigationView navView = findViewById(R.id.nav_view);

    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_home, R.id.navigation_account, R.id.navigation_placeit)
        .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupWithNavController(navView, navController);


    //Account acc = respository.get(1);
  }

}
