package com.ez.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ez.newsapp.Activities.TestActivity;

//import com.ez.newsapp.Activities.ExampleMain;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setTheme(R.style.AppTheme);
        this.getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable () {

            @Override
            public void run() {
                Intent homeIntent = new Intent (SplashActivity.this, TestActivity.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);


    }
}
