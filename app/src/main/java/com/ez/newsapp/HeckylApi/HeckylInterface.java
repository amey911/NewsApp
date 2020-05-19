package com.ez.newsapp.HeckylApi;

import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeckylInterface {

//
//    @GET("top-headLines")
//    Call<News> getNews(
//
//            @Query("country")String country,
//            @Query("apiKey") String apiKey
//
//    );
//
//    @GET("everything")
//    Call<News> getNewsSearch(
//
//            @Query("q") String keyword,
//            @Query("language") String language,
//            @Query("sortBy") String sortBy,
//            @Query("apiKey") String apiKey
//
//
//    );
//

    @GET("GetLatestTrendingNews")
    Call<HeckylNews> getHeckylNews(


            @Query("asset") String asset,
            @Query("entitytype") String entitytype,
            @Query("entitycode") String entitycode,
            @Query("lft") String lft,
            @Query("sortby") String sortyby




    );




}
