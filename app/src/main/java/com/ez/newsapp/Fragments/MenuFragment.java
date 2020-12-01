package com.ez.newsapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ez.newsapp.Activities.HeckylProfileActivity;
import com.ez.newsapp.Activities.UserPrefActivity;
import com.ez.newsapp.Activities.WelcomeActivity;
import com.ez.newsapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class MenuFragment extends Fragment {


    Button profileBtn, logoutBtn, userPref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v =  inflater.inflate(R.layout.fragment_menu, container,false);


        profileBtn = v.findViewById(R.id.menu_btn_profile);
        logoutBtn = v.findViewById(R.id.menu_btn_logout);
        userPref = v.findViewById(R.id.menu_btn_user_pref);


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), HeckylProfileActivity.class);
                startActivity(intent);

            }
        });



        userPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(getActivity(), UserPrefActivity.class);
            startActivity(intent);

            }
        });





        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
                getActivity().finish();
            }
        });





        return v;
    }



}
