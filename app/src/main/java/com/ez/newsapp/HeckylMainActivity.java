package com.ez.newsapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeckylMainActivity extends AppCompatActivity {

    private RecyclerView heckylRecView;
    private RecyclerView.LayoutManager layoutManager;
    private List<NewsItems> newsItems = new ArrayList<>();
    private HeckylAdapter heckylAdapter;
    private String TAG = HeckylMainActivity.class.getSimpleName();



    public static final String asset = "1";
    public static final String entitiytype = "ISIN";
    public static final String entitycode = "INE009A01021";
    public static final String lft = "1448843117";
    public static final String sortby = "1" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heckyl_main_activity);


        heckylRecView = findViewById(R.id.hecky_main_rec_view);
        layoutManager = new LinearLayoutManager(HeckylMainActivity.this);
        heckylRecView.setLayoutManager(layoutManager);
        heckylRecView.setItemAnimator(new DefaultItemAnimator());
        heckylRecView.setNestedScrollingEnabled(false);

       LoadHeckylNews();

    }

    public void LoadHeckylNews() {
        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> callNews;

//        callNews = heckylInterface.getHeckylNews(asset, entitiytype, entitycode, lft, sortby);

        callNews = heckylInterface.getHeckylNews(asset, entitiytype, entitycode, lft,sortby);

        callNews.enqueue(new Callback<HeckylNews>() {
            @Override
            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {
                if (response.isSuccessful() && response.body().getNewsItems() != null){

                    if (newsItems.isEmpty()){
                        newsItems.clear();

                    }
                    newsItems = response.body().getNewsItems();
                    heckylAdapter = new HeckylAdapter(newsItems, HeckylMainActivity.this);
                    heckylRecView.setAdapter(heckylAdapter);
                    heckylAdapter.notifyDataSetChanged();


                }else {
                    Toast.makeText(HeckylMainActivity.this, "No Result!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<HeckylNews> call, Throwable t) {

            }
        });

    }





}
