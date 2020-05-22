package com.ez.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.ez.newsapp.Adapters.CustomAdapter;

import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeckylActivity extends AppCompatActivity {


    private RecyclerView heckylRecView;
    private RecyclerView.LayoutManager layoutManager;
   private List<NewsItems> newsItems = new ArrayList<>();


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


        heckylRecView = findViewById(R.id.heckmain_rec_view);
        layoutManager = new LinearLayoutManager(HeckylActivity.this);
        heckylRecView.setLayoutManager(layoutManager);
        heckylRecView.setItemAnimator(new DefaultItemAnimator());
        heckylRecView.setNestedScrollingEnabled(false);
        heckylRecView.hasFixedSize();




        loadNews();

    }





    public void loadNews() {

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


                 }else {
                     Toast.makeText(HeckylActivity.this, "No Result!", Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<HeckylNews> call, Throwable t) {

             }
         });

    }














}
