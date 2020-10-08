package com.ez.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ez.newsapp.Adapters.CustomAdapter;
import com.ez.newsapp.Adapters.HomeNewsAdapter;
import com.ez.newsapp.HeckylActivity;
import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.NewsDetailActivity;
import com.ez.newsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView HomeTopNewsRec;

    private List<NewsItems> topNewsItems = new ArrayList<>();
    private HomeNewsAdapter TopNewsAdapter;

    private String newsCount = "10";


    BottomNavigationView bottomNavHome;


    String responseLft = "0";


    TextView viewAllTopNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        viewAllTopNews = findViewById(R.id.home_top_news_view_all);


        HomeTopNewsRec = findViewById(R.id.home_top_rec_view);
        HomeTopNewsRec.setLayoutManager(new LinearLayoutManager(this));
        HomeTopNewsRec.setItemAnimator(new DefaultItemAnimator());
        HomeTopNewsRec.setNestedScrollingEnabled(true);

        HomeTopNewsRec.hasFixedSize();


        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        bottomNavHome= findViewById(R.id.bottom_nav_home);
        bottomNavHome.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_bar_item_home:
                        Toast.makeText(HomeActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bottom_bar_item_markets:
                        Toast.makeText(HomeActivity.this, "Markets", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bottom_bar_item_media:
                        Toast.makeText(HomeActivity.this, "Media", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bottom_bar_item_menu:
                        Toast.makeText(HomeActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });





        LoadTopNews("1", "ISIN", "US38259P7069|US0378331005", responseLft, "2");



        viewAllTopNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HomeActivity.this, "View all news", Toast.LENGTH_SHORT).show();

            }
        });


    }



    private void LoadTopNews(final String asset , final String entityType, final String entityCodeList, final String lft, final String sortKey)
    {
// for list of companies

        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> callTopNews;

        callTopNews = heckylInterface.getNewsForEntities(asset, entityType, entityCodeList,newsCount, lft, sortKey);

        callTopNews.enqueue(new Callback<HeckylNews>() {
            @Override
            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {


                if (response.isSuccessful() && response.body().getNewsItems() !=null) {

                    if (topNewsItems.isEmpty()) {
                        topNewsItems.clear();
                    }

                    topNewsItems = response.body().getNewsItems();
                    responseLft = response.body().getLft();

                    Toast.makeText(HomeActivity.this, "Response LFT" + responseLft, Toast.LENGTH_SHORT).show();

                    TopNewsAdapter = new HomeNewsAdapter(topNewsItems, HomeActivity.this);
                    HomeTopNewsRec.setAdapter(TopNewsAdapter);
                    TopNewsAdapter.notifyDataSetChanged();



                    initListener();


                }
                else {
//


                    Toast.makeText(HomeActivity.this, "No Result!", Toast.LENGTH_LONG).show();
                }




            }

            @Override
            public void onFailure(Call<HeckylNews> call, Throwable t) {

            }
        });








    }
    private void initListener() {
        TopNewsAdapter.setOnItemClickListener(new HomeNewsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(HomeActivity.this, NewsDetailActivity.class);

                NewsItems news = topNewsItems.get(position);

                intent.putExtra("title", news.getTitle());
                intent.putExtra("url", news.getLink());
                intent.putExtra("source", news.getSource());
                intent.putExtra("author", news.getName());

                startActivity(intent);
            }
        });
    }
}
