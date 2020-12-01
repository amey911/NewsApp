package com.ez.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.ez.newsapp.R;

public class UserPrefActivity extends AppCompatActivity {

    RecyclerView userPrefCompaniesRc;
    Button userPrefSaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pref);

        userPrefCompaniesRc = findViewById(R.id.userpref_companies_rc);
        userPrefSaveBtn = findViewById(R.id.userpref_save_btn);

    }


    



}