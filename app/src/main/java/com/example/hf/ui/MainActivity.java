package com.example.hf.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hf.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
