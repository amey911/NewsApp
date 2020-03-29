package com.ez.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
//import android.widget.SearchView;


import android.widget.TextView;
import android.widget.Toast;

import com.ez.newsapp.Api.ApiClient;
import com.ez.newsapp.Api.ApiInterface;
import com.ez.newsapp.Models.Article;
import com.ez.newsapp.Models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public  static final String API_KEY = "09803f739fe4493396c5456cfa410330 ";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private TextView topHeadline;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
    swipeRefreshLayout.setOnRefreshListener(this);
    swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


    topHeadline = findViewById(R.id.topHeadlines);

        recyclerView = findViewById(R.id.main_rec_view);
//        layoutManager = new LinearLayoutManager(MainActivity.this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(layoutManager);

        recyclerView.hasFixedSize();


//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);



//        mAdapter = new CountryAdapter(CountryManager.getInstance().getCountries(), R.layout.card_layout, getActivity());



        onLoadingSwipeRefresh("");

    }


    public void LoadJason(final String keyword) {

        topHeadline.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String country = Utils.getCountry();
        String language = Utils.getLanguage();

        Call<News> call;


        if (keyword.length() > 0 ) {
            call = apiInterface.getNewsSearch(keyword, language, "publishedAt", API_KEY);

        } else {
            call = apiInterface.getNews(country, API_KEY);
        }




        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null ){

                    if (!articles.isEmpty()){
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new Adapter(articles, MainActivity.this);

                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    initListener();

                    topHeadline.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);

                    Log.e(TAG, "onResponse: data recived " );

                }else {
                    topHeadline.setVisibility(View.VISIBLE);

                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                topHeadline.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    private void initListener() {
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ImageView imageView = view.findViewById(R.id.img);



                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);

                Article article = articles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img", article.getUrlToImage());
                intent.putExtra("date", article.getPublishedAt());
                intent.putExtra("source", article.getSource().getName());
                intent.putExtra("author", article.getAuthor());

                Pair<View, String> pair = Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this,
                        pair
                );



                startActivity(intent, optionsCompat.toBundle());

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search).getActionView();


        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Latest News...");





                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 2) {

                   topHeadline.setText("Search Results For: " +query);

                    onLoadingSwipeRefresh(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                LoadJason(newText);
                return false;
            }
        });



        searchMenuItem.getIcon().setVisible(false, false);


        return true;
    }

    @Override
    public void onRefresh() {
        topHeadline.setText("Top Headlines");

    LoadJason("");
    }

    private void onLoadingSwipeRefresh(final String keyword) {

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        LoadJason(keyword);
                    }
                }

        );
    }



}
