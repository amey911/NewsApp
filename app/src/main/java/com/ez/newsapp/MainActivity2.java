package com.ez.newsapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ez.newsapp.Fragments.NewsFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity2 extends AppCompatActivity {
    BottomBar bottomBar;

    Fragment setFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         bottomBar = findViewById(R.id.bottom_bar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {

                if (tabId == R.id.tab_home){

                    setFragment = new NewsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, setFragment).commit();


                }

                else if(tabId == R.id.tab_markets){

                    // inflate market fragment


                }
                else if (tabId == R.id.tab_watchlist) {

                    // inflate wathlist fragment



                }
            }
        });







    }
}
