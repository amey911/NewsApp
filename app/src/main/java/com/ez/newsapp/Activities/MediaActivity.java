package com.ez.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ez.newsapp.Adapters.CustomAdapter;
import com.ez.newsapp.Adapters.MediaAdapter;
import com.ez.newsapp.HeckylActivity;
import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.Categories;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.NewsDetailActivity;
import com.ez.newsapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView mediaRecView;
    private RecyclerView.LayoutManager layoutManager;
    private List<NewsItems> mediaItems = new ArrayList<>();
    private List<Categories> categories = new ArrayList<>();


    private MediaAdapter mediaAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;


    String responseLft = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);



        swipeRefreshLayout = findViewById(R.id.swipe_refresh_media);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        mediaRecView = findViewById(R.id.media_rec_view);
        layoutManager = new LinearLayoutManager(MediaActivity.this);
        mediaRecView.setLayoutManager(layoutManager);
        mediaRecView.setItemAnimator(new DefaultItemAnimator());
        mediaRecView.setNestedScrollingEnabled(false);
        mediaRecView.hasFixedSize();

        LoadMedia("0");


    }




    private void LoadMedia(String lft)
    {

        swipeRefreshLayout.setRefreshing(true);


        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        Call<HeckylNews> call;

        call = heckylInterface.getVideoNews(lft);

        call.enqueue(new Callback<HeckylNews>() {
            @Override
            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {




                if (response.isSuccessful() && response.body().getNewsItems() !=null){

                    if (mediaItems.isEmpty()){
                        mediaItems.clear();
                    }



                    mediaItems = response.body().getNewsItems();
                    responseLft = response.body().getLft();

//                     lft = responseLft;

                    Toast.makeText(MediaActivity.this, "response lft: " +responseLft,  Toast.LENGTH_SHORT).show();



                    mediaAdapter = new MediaAdapter(mediaItems, MediaActivity.this);
                    mediaRecView.setAdapter(mediaAdapter);
                    mediaAdapter.notifyDataSetChanged();

                    initListener();


//                     topHeadline.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);




                }else {
//                     topHeadline.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);

                    Toast.makeText(MediaActivity.this, "No Result!", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<HeckylNews> call, Throwable t) {

            }
        });



    }

    private void initListener() {

        mediaAdapter.setOnItemClickListener(new MediaAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(MediaActivity.this, NewsDetailActivity.class);

                NewsItems news = mediaItems.get(position);

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
        LoadMedia("0");
    }
}
