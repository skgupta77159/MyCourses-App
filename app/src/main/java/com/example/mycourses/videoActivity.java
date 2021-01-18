package com.example.mycourses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class videoActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        url = getIntent().getStringExtra("VidUrl");
    }
}