package com.ez.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ez.newsapp.Adapters.CustomAdapter;

import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.Categories;
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
   private List<Categories> categories = new ArrayList<>();
    private TextView topHeadline;
    private SwipeRefreshLayout swipeRefreshLayout;

    HeckylNews heckylNews;
    private CustomAdapter heckylAdapter;



    public static final String asset = "1";
    public static final String entitiytype = "ISIN";
    public static final String entitycode = "INE009A01021";
    public static String lft = "0";

    String responseLft = "";

    private String sortby = "1";



    private static String sortByLatest = "1";
    private static String sortbyTrending = "2" ;








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

//        LoadNewsLatest();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);




//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId())
        {

            case R.id.item_latest:
                sortby = "1";

                LoadNewsLatestTrending(sortByLatest);
                break;

            case R.id.item_trending:
                sortby = "2";

                LoadNewsLatestTrending(sortbyTrending);
                break;
        }

        switch (item.getItemId())
        {
            case R.id.region_australia:


                item.setChecked(true);
                LoadRegionNews("16");

                topHeadline.setText("News of Region: "+"Australia ");

                break;

            case R.id.region_hongkong:
                LoadRegionNews("92");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"HongKong ");
                break;

            case R.id.region_india:
                LoadRegionNews("1");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"India ");
                break;

            case R.id.region_indonesia:
                LoadRegionNews("95");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Indonesia ");
                break;

                case R.id.region_malaysia:
                    LoadRegionNews("122");
                    item.setChecked(true);
                    topHeadline.setText("News of Region: "+"Malaysia ");
                break;

            case R.id.region_singapore:
                LoadRegionNews("181");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Singapore ");
                break;

            case R.id.region_uk :
                LoadRegionNews("211");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"UK ");
                break;

            case R.id.region_us:
                LoadRegionNews("2");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"US ");
                break;

            case R.id.region_belgium:
                LoadRegionNews("24");
                topHeadline.setText("News of Region: "+"Belgium ");
                break;

            case R.id.region_canada:
                LoadRegionNews("3");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Canada ");
                break;

            case R.id.region_china:
                LoadRegionNews("45");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"China ");
                break;

            case R.id.region_denmark:
                LoadRegionNews("57");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Denmark ");
                break;

            case R.id.region_finland:
                LoadRegionNews("70");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Finland ");
                break;

            case R.id.region_iceland:
                LoadRegionNews("94");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Iceland ");
                break;

            case R.id.region_italy:
                LoadRegionNews("100");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Italy ");
                break;

            case R.id.region_japan:
                LoadRegionNews("102");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Japan ");
                break;

            case R.id.region_netherlands:
                LoadRegionNews("145");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Netherlands ");
                break;


            case R.id.region_norway:
                LoadRegionNews("151");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Norway ");
                break;

            case R.id.region_poland:
                LoadRegionNews("161");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Poland ");
                break;

            case R.id.region_portugal:
                LoadRegionNews("162");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Portugal ");
                break;

            case R.id.region_russia:
                LoadRegionNews("167");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Russia ");
                break;

            case R.id.region_southkorea:
                LoadRegionNews("187");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"South Korea ");
                break;

            case R.id.region_spain:
                LoadRegionNews("188");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Spain ");
                break;

            case R.id.region_sweden:
                LoadRegionNews("193");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Sweden ");
                break;

            case R.id.region_switzerland:
                LoadRegionNews("194");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Switzerland ");
                break;

            case R.id.region_germany:
                LoadRegionNews("78");
                item.setChecked(true);
                topHeadline.setText("News of Region: "+"Germany ");
                break;

        }




        return true;
//        return super.onOptionsItemSelected(item);

    }






    public void LoadNewsLatestTrending(final String sortKey) {

        if (sortKey == "1") {
            sortby = "1";
            topHeadline.setText("Latest News");
            topHeadline.setVisibility(View.VISIBLE);

        } else {
            topHeadline.setText("Trending News");
            topHeadline.setVisibility(View.VISIBLE);

            sortby = "2";
        }


        swipeRefreshLayout.setRefreshing(true);

        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> call;

        call = heckylInterface.getNews(asset, entitiytype, entitycode, lft, sortKey);

         call.enqueue(new Callback<HeckylNews>() {



             @Override
             public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {






                 if (response.isSuccessful() && response.body().getNewsItems() !=null){

                     if (newsItems.isEmpty()){
                         newsItems.clear();
                     }



                     newsItems = response.body().getNewsItems();
                     responseLft = response.body().getLft();

//                     lft = responseLft;

                     Toast.makeText(HeckylActivity.this, "response lft: " +responseLft,  Toast.LENGTH_SHORT).show();



                     heckylAdapter = new CustomAdapter(newsItems, HeckylActivity.this);
                     heckylRecView.setAdapter(heckylAdapter);
                     heckylAdapter.notifyDataSetChanged();

                     initListener();


//                     topHeadline.setVisibility(View.INVISIBLE);
                     swipeRefreshLayout.setRefreshing(false);




                 }else {
//                     topHeadline.setVisibility(View.INVISIBLE);
                     swipeRefreshLayout.setRefreshing(false);

                     Toast.makeText(HeckylActivity.this, "No Result!", Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<HeckylNews> call, Throwable t) {

             }
         });

    }












    public void LoadRegionNews(final String RegionCode) {

//        topHeadline.setText("Latest News of Region" +RegionCode);


        swipeRefreshLayout.setRefreshing(true);

        final HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> callRegionNews;

        callRegionNews = heckylInterface.getRegionNews("1", "0", RegionCode);

        callRegionNews.enqueue(new Callback<HeckylNews>() {
            @Override
            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {

                if (response.isSuccessful() && response.body().getNewsItems() !=null) {
                    if (newsItems.isEmpty()) {
                        newsItems.clear();
                    }

                    newsItems = response.body().getNewsItems();
                    heckylAdapter = new CustomAdapter(newsItems, HeckylActivity.this);
                    heckylRecView.setAdapter(heckylAdapter);
                    heckylAdapter.notifyDataSetChanged();



                    initListener();

                    swipeRefreshLayout.setRefreshing(false);
                } else
                {
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

        if (sortby == "1"){
            LoadNewsLatestTrending(sortbyTrending);
        } else {
            LoadNewsLatestTrending(sortByLatest);
        }


//        LoadNewsTredning();
    }

    private void onLoadingSwipeRefresh() {
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        if (sortby == "1"){
                            LoadNewsLatestTrending(sortByLatest);
                        } else {
                            LoadNewsLatestTrending(sortbyTrending);
                        }
                    }
                }
        );
    }
}
