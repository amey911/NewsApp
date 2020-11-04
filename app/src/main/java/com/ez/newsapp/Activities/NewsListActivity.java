package com.ez.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ez.newsapp.Adapters.CustomAdapter;
import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.R;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListActivity extends AppCompatActivity {

    RecyclerView newsListRec;
    Spinner newsListSortSpinner;
    RecyclerView.LayoutManager layoutManager;
    MultiStateToggleButton newsListAssetToggle;

    CustomAdapter newsListAdapter;

    private List<NewsItems> newsItems = new ArrayList<>();

    String ASSET_VALUE = "1";
    String SORT_VALUE = "1";

    private String responseLft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        newsListRec = findViewById(R.id.news_list_rec);
        newsListSortSpinner = findViewById(R.id.news_list_sort_spinner);
        newsListAssetToggle = findViewById(R.id.news_list_asset_toggle);


        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources()
                .getStringArray(R.array.sorting));

        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newsListSortSpinner.setAdapter(sortAdapter);


        newsListSortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int sortVal = position+1;


                SORT_VALUE = String.valueOf(sortVal);

                LoadTopNews("0", "ISIN", "US38259P7069|US0378331005|INE009A01021", "0", SORT_VALUE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        newsListAssetToggle.setValue(0);
        newsListAssetToggle.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {

                int assetVal = value+1;

                ASSET_VALUE = String.valueOf(assetVal);

                LoadTopNews("0", "ISIN", "US38259P7069|US0378331005|INE009A01021", "0", SORT_VALUE);

            }
        });




        layoutManager = new LinearLayoutManager(NewsListActivity.this);
        newsListRec.setLayoutManager(layoutManager);
        newsListRec.setItemAnimator(new DefaultItemAnimator());
        newsListRec.setNestedScrollingEnabled(true);
        newsListRec.hasFixedSize();




        LoadTopNews("1", "ISIN", "US38259P7069|US0378331005", "0", "1");

    }














    private void LoadTopNews(final String asset , final String entityType, final String entityCodeList, final String lft, final String sortKey)
    {
// for list of companies





        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);


        Call<HeckylNews> callTopNews;

        callTopNews = heckylInterface.getNewsForEntities(asset, entityType, entityCodeList, "0", lft, sortKey);

        callTopNews.enqueue(new Callback<HeckylNews>() {
            @Override
            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {

                if (response.isSuccessful() && response.body().getNewsItems() !=null) {

                   if (newsItems.isEmpty()) {
                       newsItems.clear();
                   }

                   newsItems = response.body().getNewsItems();
                   responseLft = response.body().getLft();


                   newsListAdapter = new CustomAdapter(newsItems, NewsListActivity.this);

                   newsListRec.setAdapter(newsListAdapter);
                   newsListRec.setHasFixedSize(true);
                   newsListAdapter.notifyDataSetChanged();


                }

                else {

                    Toast.makeText(NewsListActivity.this, "News not available", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HeckylNews> call, Throwable t) {

            }
        });


    }





    public void LoadRegionNews(final String RegionCode) {


        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> callRegionNews;

        callRegionNews = heckylInterface.getRegionNews("1", "0", RegionCode);

        callRegionNews.enqueue(new Callback<HeckylNews>() {
            @Override
            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {

                if (response.isSuccessful() && response.body().getNewsItems() !=null){

                    if (newsItems.isEmpty()) {
                        newsItems.clear();
                    }


                    newsItems = response.body().getNewsItems();
                    newsListAdapter = new CustomAdapter(newsItems, NewsListActivity.this);
                    newsListRec.setAdapter(newsListAdapter);
                    newsListRec.setHasFixedSize(true);
                    newsListAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<HeckylNews> call, Throwable t) {

            }
        });


    }










}
