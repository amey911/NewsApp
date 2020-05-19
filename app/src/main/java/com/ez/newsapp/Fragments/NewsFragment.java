package com.ez.newsapp.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ez.newsapp.Adapter;
import com.ez.newsapp.Api.ApiClient;
import com.ez.newsapp.Api.ApiInterface;
import com.ez.newsapp.MainActivity;
import com.ez.newsapp.MainActivity2;
import com.ez.newsapp.Models.Article;
import com.ez.newsapp.Models.News;
import com.ez.newsapp.NewsDetailActivity;
import com.ez.newsapp.R;
import com.ez.newsapp.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment  {
    public static final String API_KEY = "09803f739fe4493396c5456cfa410330 ";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private TextView topHeadline;
    private SwipeRefreshLayout swipeRefreshLayout;

    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;
    public String Query = "";


    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        errorLayout = container.findViewById(R.id.errorLayout);
        errorImage = container.findViewById(R.id.errorImage);
        errorTitle = container.findViewById(R.id.errorTitle);
        errorMessage = container.findViewById(R.id.errorMessage);
        btnRetry = container.findViewById(R.id.btnRetry);

        topHeadline = container.findViewById(R.id.topHeadlines);

        recyclerView = container.findViewById(R.id.main_rec_view);


        v = inflater.inflate(R.layout.fragment_news, container, false);
        return v;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void LoadNews(final String keyword) {


        errorLayout.setVisibility(View.GONE);


        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String country = Utils.getCountry();
        String language = Utils.getLanguage();

        Call<News> call;


        if (keyword.length() > 0) {
            call = apiInterface.getNewsSearch(keyword, language, "publishedAt", API_KEY);

        } else {
            call = apiInterface.getNews(country, API_KEY);
        }


        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {


                    if (!articles.isEmpty()) {
                        articles.clear();
                    }

                    articles = response.body().getArticle();
//                    adapter = new Adapter(articles, MainActivity.this);

                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    topHeadline.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                } else {


                    swipeRefreshLayout.setRefreshing(false);
//                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();

                    String errorCode;

                    switch (response.code()) {
                        case 404:
                            errorCode = "404 not found";
                            break;

                        case 500:
                            errorCode = "500 server broken";
                            break;

                        default:
                            errorCode = "unknown error";
                            break;


                    }


                    showErrorMessage(R.drawable.no_result,
                            "No Result", "Please Try Again\n" +
                                    errorCode);

                }


            }


            @Override
            public void onFailure(Call<News> call, Throwable t) {
                topHeadline.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);

                showErrorMessage(R.drawable.no_result,
                        "....", "Network failure, Please try again\n" +
                                t.toString());


            }
        });


    }

//    private void initListener() {
//        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ImageView imageView = view.findViewById(R.id.img);
//
//                Intent intent = new Intent(MainActivity2.this, NewsDetailActivity.class);
//
//                Article article = articles.get(position);
//                intent.putExtra("url", article.getUrl());
//                intent.putExtra("title", article.getTitle());
//                intent.putExtra("img", article.getUrlToImage());
//                intent.putExtra("date", article.getPublishedAt());
//                intent.putExtra("source", article.getSource().getName());
//                intent.putExtra("author", article.getAuthor());
//
//                Pair<View, String> pair = Pair.create((View) imageView, ViewCompat.getTransitionName(imageView));
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        NewsFragment.this,
//                        pair);
//
//                startActivity(intent, optionsCompat.toBundle());
//
//
//            }
//        });
//
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {




        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
////        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//
//        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
//
//
//        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setQueryHint("Search Latest News...");
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


    private  void showErrorMessage( int imageView , String title , String message  ) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onLoadingSwipeRefresh("");

                LoadNews("");

            }
        });



    }
    }





