package com.ez.newsapp.Fragments;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
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
import androidx.viewpager.widget.ViewPager;

import com.ez.newsapp.Activities.HeckylHome;
import com.ez.newsapp.Activities.HomeActivity;
import com.ez.newsapp.Activities.NewsListActivity;
import com.ez.newsapp.Adapters.HomeNewsAdapter;
import com.ez.newsapp.Adapters.SwipeAdapter;
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

    Spinner homeRegionSpinner;



    ViewPager viewPager;
    SwipeAdapter swipeAdapter;
    Integer[] colors = null;
    List<NewsItems> swipeItems;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();



    TextView viewAllTopNews;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container,false);





        viewAllTopNews = view.findViewById(R.id.heckylhome_top_news_view_all);
        heckylHomeTopNewsRec = view.findViewById(R.id.heckylhome_top_rec_view);
        heckylRegionNewsRec = view.findViewById(R.id.heckylhome_region_news_rec);

        homeRegionSpinner = view.findViewById(R.id.home_region_spinner);


        ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, getResources()
                .getStringArray(R.array.regions));

        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        homeRegionSpinner.setAdapter(regionAdapter);




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






        viewAllTopNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent (getActivity(), NewsListActivity.class);
                startActivity(homeIntent);


            }
        });




        homeRegionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                switch (position){

                    case 0:
                    LoadRegionNews("1");
                    break;

                    case 1:
                        LoadRegionNews("16");
                        break;

                    case 2:
                        LoadRegionNews("92");
                        break;


                    case 3:
                        LoadRegionNews("95");
                        break;


                    case 4:
                        LoadRegionNews("122");
                        break;


                    case 5:
                        LoadRegionNews("181");
                        break;


                    case 6:
                        LoadRegionNews("211");
                        break;


                    case 7:
                        LoadRegionNews("2");
                        break;


                    case 8:
                        LoadRegionNews("24");
                        break;

                    case 9:
                        LoadRegionNews("3");
                        break;

                    case 10:
                        LoadRegionNews("45");
                        break;

                    case 11:
                        LoadRegionNews("57");
                        break;

                    case 12:
                        LoadRegionNews("70");
                        break;

                    case 13:
                        LoadRegionNews("94");
                        break;


                    case 14:
                        LoadRegionNews("100");
                        break;

                    case 15:
                        LoadRegionNews("102");
                        break;


                    case 16:
                        LoadRegionNews("142");
                        break;

                    case 17:
                        LoadRegionNews("151");
                        break;

                    case 18:
                        LoadRegionNews("161");
                        break;

                    case 19:
                        LoadRegionNews("162");
                        break;

                    case 20:
                        LoadRegionNews("167");
                        break;

                    case 21:
                        LoadRegionNews("187");
                        break;

                    case 22:
                        LoadRegionNews("188");
                        break;

                    case 23:
                        LoadRegionNews("193");
                        break;

                    case 24:
                        LoadRegionNews("194");
                        break;

                    case 25:
                        LoadRegionNews("78");
                        break;




                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return view;

    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);







    }






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        LoadTopNews("1", "ISIN", "US38259P7069|US0378331005|INE009A01021", "0", "2");
        LoadRegionNews("1");

//        final FragmentActivity c = getActivity();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                c.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                    }
//                });
//            }
//        }).start();

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
