package com.ez.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.ez.newsapp.Fragments.HomeFragment;
import com.ez.newsapp.Fragments.MarketsFragment;
import com.ez.newsapp.Fragments.MediaFragment;
import com.ez.newsapp.Fragments.MenuFragment;
import com.ez.newsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HeckylHome extends AppCompatActivity {

    BottomNavigationView bottomNavHeckylHome;


    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heckyl_home);

        bottomNavHeckylHome = findViewById(R.id.bottom_nav_heckyl_home);

        bottomNavHeckylHome.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {


                        case R.id.bottom_bar_item_home :
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.bottom_bar_item_markets :
                            selectedFragment = new MarketsFragment();
                            break;

                        case R.id.bottom_bar_item_media :
                            selectedFragment = new MediaFragment();
                            break;

                        case R.id.bottom_bar_item_menu :
                            selectedFragment = new MenuFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;

                }
            };


    @Override
    public void onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
