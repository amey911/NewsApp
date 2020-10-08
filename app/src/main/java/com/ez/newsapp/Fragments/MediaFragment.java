package com.ez.newsapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ez.newsapp.Activities.HeckylHome;
import com.ez.newsapp.Activities.MediaActivity;
import com.ez.newsapp.Adapters.MediaAdapter;
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

public class MediaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {



    private RecyclerView mediaRecView;
    private RecyclerView.LayoutManager layoutManager;
    private List<NewsItems> mediaItems = new ArrayList<>();
    private List<Categories> categories = new ArrayList<>();


    private MediaAdapter mediaAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;


    String responseLft = "";





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//       return inflater.inflate(R.layout.fragment_media, container,false);

        View view = inflater.inflate(R.layout.fragment_media, container,false);

        mediaRecView = view.findViewById(R.id.media_rec_view_frag);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_media_frag);
        swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        layoutManager = new LinearLayoutManager(getActivity());
        mediaRecView.setLayoutManager(layoutManager);
        mediaRecView.setItemAnimator(new DefaultItemAnimator());
        mediaRecView.setNestedScrollingEnabled(false);
        mediaRecView.hasFixedSize();





        LoadMedia("0");

        return view;

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






                    mediaAdapter = new MediaAdapter(mediaItems, getActivity());
                    mediaRecView.setAdapter(mediaAdapter);




                    mediaAdapter.notifyDataSetChanged();

                    initListener();


//                     topHeadline.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);




                }else {
//                     topHeadline.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);


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
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);

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
