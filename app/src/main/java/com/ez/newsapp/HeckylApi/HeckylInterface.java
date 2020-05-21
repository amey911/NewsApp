package com.ez.newsapp.HeckylApi;

import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.Models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeckylInterface {

@GET("GetLatestTrendingNews")
    Call<HeckylNews> getHeckylNews(

        @Query("asset") int asset,
        @Query("entitytype") String entityType,
        @Query("entitycode") String entityCode,
        @Query("lft") int lft,
        @Query("sortby") int sortBy

        );






}
