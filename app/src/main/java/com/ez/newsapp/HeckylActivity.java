package com.ez.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ez.newsapp.Adapters.CustomAdapter;

import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.Models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeckylActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    private RecyclerView heckylRecView;
    private RecyclerView.LayoutManager layoutManager;
   private List<NewsItems> newsItems = new ArrayList<>();
    private TextView topHeadline;
    private SwipeRefreshLayout swipeRefreshLayout;

    HeckylNews heckylNews;
    private CustomAdapter heckylAdapter;



    public static final String asset = "1";
    public static final String entitiytype = "ISIN";
    public static final String entitycode = "INE009A01021";
    public static final String lft = "1448843117";
    public static final String sortby = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heckyl);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        topHeadline = findViewById(R.id.topHeadlines);
        heckylRecView = findViewById(R.id.heckmain_rec_view);
        layoutManager = new LinearLayoutManager(HeckylActivity.this);
        heckylRecView.setLayoutManager(layoutManager);
        heckylRecView.setItemAnimator(new DefaultItemAnimator());
        heckylRecView.setNestedScrollingEnabled(false);
        heckylRecView.hasFixedSize();

        topHeadline = findViewById(R.id.topHeadlines);




       onLoadingSwipeRefresh();



    }





    public void loadNews() {

        topHeadline.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(true);

        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> call;

        call = heckylInterface.getNews(asset, entitiytype, entitycode, lft, sortby);

         call.enqueue(new Callback<HeckylNews>() {
             @Override
             public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {
                 if (response.isSuccessful() && response.body().getNewsItems() !=null){

                     if (newsItems.isEmpty()){
                         newsItems.clear();
                     }

                     newsItems = response.body().getNewsItems();
                     heckylAdapter = new CustomAdapter(newsItems, HeckylActivity.this);
                     heckylRecView.setAdapter(heckylAdapter);
                     heckylAdapter.notifyDataSetChanged();

                     initListener();


                     topHeadline.setVisibility(View.INVISIBLE);
                     swipeRefreshLayout.setRefreshing(false);

                 }else {
                     topHeadline.setVisibility(View.INVISIBLE);
                     swipeRefreshLayout.setRefreshing(false);

                     Toast.makeText(HeckylActivity.this, "No Result!", Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<HeckylNews> call, Throwable t) {

             }
         });

    }


    private void initListener() {
        heckylAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(HeckylActivity.this, NewsDetailActivity.class);

                NewsItems news = newsItems.get(position);

                intent.putExtra("title", news.getTitle());
                intent.putExtra("url", news.getLink());
                intent.putExtra("source", news.getSource());
                intent.putExtra("author", news.getName());

                startActivity(intent);

//                Article article = articles.get(position);
//                intent.putExtra("url", article.getUrl());
//                intent.putExtra("title", article.getTitle());
//                intent.putExtra("img", article.getUrlToImage());
//                intent.putExtra("date", article.getPublishedAt());
//                intent.putExtra("source", article.getSource().getName());
//                intent.putExtra("author", article.getAuthor());
//
//                Pair<View, String> pair = Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        MainActivity.this,
//                        pair
//                );
//                startActivity(intent, optionsCompat.toBundle());
//            }



            }
        });
    }





    @Override
    public void onRefresh() {
        loadNews();
    }

    private void onLoadingSwipeRefresh() {
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        loadNews();
                    }
                }
        );
    }
}
