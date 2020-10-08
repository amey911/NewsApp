package com.ez.newsapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ez.newsapp.Activities.HomeActivity;
import com.ez.newsapp.Adapters.HomeNewsAdapter;
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

public class HomeFragment extends Fragment {



    private RecyclerView heckylHomeTopNewsRec, heckylRegionNewsRec;

    private List<NewsItems> topNewsItems = new ArrayList<>();
    private List<NewsItems> regionNewsItems = new ArrayList<>();

    private HomeNewsAdapter TopNewsAdapter;
    private HomeNewsAdapter regionNewsAdapter;

    private String newsCount = "10";

    String responseLft = "0";

    private RecyclerView.LayoutManager layoutManager;

    TextView viewAllTopNews;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container,false);


        heckylHomeTopNewsRec = view.findViewById(R.id.heckylhome_top_rec_view);
        heckylRegionNewsRec = view.findViewById(R.id.heckylhome_region_news_rec);


        layoutManager = new LinearLayoutManager(getActivity());



        TopNewsAdapter = new HomeNewsAdapter(topNewsItems, getActivity());

        heckylHomeTopNewsRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        heckylHomeTopNewsRec.setItemAnimator(new DefaultItemAnimator());
        heckylHomeTopNewsRec.setNestedScrollingEnabled(true);

        heckylHomeTopNewsRec.setHasFixedSize(true);


        regionNewsAdapter = new HomeNewsAdapter(regionNewsItems, getActivity());

        heckylRegionNewsRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        heckylRegionNewsRec.setItemAnimator(new DefaultItemAnimator());
        heckylRegionNewsRec.setNestedScrollingEnabled(true);

        heckylRegionNewsRec.setHasFixedSize(true);










        return view;

    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);







    }






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FragmentActivity c = getActivity();

        new Thread(new Runnable() {
            @Override
            public void run() {

                c.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoadTopNews("1", "ISIN", "US38259P7069|US0378331005|INE009A01021", "0", "2");

                        LoadRegionNews("1");

                    }
                });
            }
        }).start();

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


                    TopNewsAdapter = new HomeNewsAdapter(topNewsItems, getActivity());
                    heckylHomeTopNewsRec.setLayoutManager(layoutManager);
                    heckylHomeTopNewsRec.setAdapter(TopNewsAdapter);
                    TopNewsAdapter.notifyDataSetChanged();





                    initListener();


                }
                else {


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

                    if (regionNewsItems.isEmpty()) {
                        regionNewsItems.clear();
                    }


                    regionNewsItems = response.body().getNewsItems();
                    regionNewsAdapter = new HomeNewsAdapter(regionNewsItems, getActivity());
                    heckylRegionNewsRec.setAdapter(regionNewsAdapter);
                    heckylHomeTopNewsRec.setHasFixedSize(true);
                    regionNewsAdapter.notifyDataSetChanged();







                }

            }

            @Override
            public void onFailure(Call<HeckylNews> call, Throwable t) {

            }
        });


    }



    private void LoadCategories()
    {
        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);


    }





    @Override
    public void onStart() {
        super.onStart();

        LoadTopNews("1", "ISIN", "US38259P7069|US0378331005|INE009A01021", "0", "2");

    }




    private void initListener() {
        TopNewsAdapter.setOnItemClickListener(new HomeNewsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);

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
