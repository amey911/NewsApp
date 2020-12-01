package com.ez.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ez.newsapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    Button signinBtn, signupBtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        signinBtn = findViewById(R.id.welcome_sign_in);
        signupBtn = findViewById(R.id.welcome_sign_up);

        mAuth= FirebaseAuth.getInstance();

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(WelcomeActivity.this, HeckylLoginActivity.class);
                startActivity(intent);
            }
        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(WelcomeActivity.this, HeckylRegisterActivity.class);
                startActivity(intent);
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null  ) {
            finish();
            startActivity(new Intent(this , HeckylHome.class));

        }
    }
}