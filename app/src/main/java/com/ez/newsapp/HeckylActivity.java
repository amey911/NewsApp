package com.ez.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeckylActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private DrawerLayout drawerLayout;


    private RecyclerView heckylRecView;
    private RecyclerView.LayoutManager layoutManager;
   private List<NewsItems> newsItems = new ArrayList<>();
    private List<Categories> categories = new ArrayList<>();


   MultiStateToggleButton assetToggle;
   Spinner sortSpinner;

    private SwipeRefreshLayout swipeRefreshLayout;

    HeckylNews heckylNews;
    private CustomAdapter heckylAdapter;



    public static final String asset = "1";
    public static final String entitiytype = "ISIN";
    public static final String entitycode = "INE009A01021";
    public static String lft = "0";

    String responseLft = "";

    private String sortby = "1";
    private String assetValue = "1";



    private static String sortByLatest = "1";
    private static String sortbyTrending = "2" ;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heckyl);




        drawerLayout = findViewById(R.id.drawer_layout);


        assetToggle = findViewById(R.id.asset_multistate);
        assetToggle.setColorRes(R.color.colorPrimaryDark, R.color.navy);
        assetToggle.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                assetValue = String.valueOf(value);

            }
        });


        sortSpinner = findViewById(R.id.sort_spinner);

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.sorting));

        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    sortby = "1";
                    onLoadingSwipeRefresh();
                } else if (position == 1) {
                    sortby = "2";
                    onLoadingSwipeRefresh();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);



        heckylRecView = findViewById(R.id.heckmain_rec_view);
        layoutManager = new LinearLayoutManager(HeckylActivity.this);
        heckylRecView.setLayoutManager(layoutManager);
        heckylRecView.setItemAnimator(new DefaultItemAnimator());
        heckylRecView.setNestedScrollingEnabled(false);
        heckylRecView.hasFixedSize();




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






        return true;
//        return super.onOptionsItemSelected(item);

    }






    public void LoadNewsLatestTrending(final String sortKey) {

        if (sortKey == "1") {
            sortby = "1";


        } else {


            sortby = "2";
        }


        swipeRefreshLayout.setRefreshing(true);

        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> call;

//        call = heckylInterface.getNews("1", "BSE", "0", lft, sortKey);

        call = heckylInterface.getNews(assetValue, entitiytype, entitycode, lft, sortKey);

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
                        } else if (sortby == "2"){
                            LoadNewsLatestTrending(sortbyTrending);
                        }
                    }
                }
        );
    }


}
