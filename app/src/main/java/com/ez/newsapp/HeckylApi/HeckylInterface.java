package com.ez.newsapp.HeckylApi;

import com.ez.newsapp.HeckylModels.CategoriesObject;
import com.ez.newsapp.HeckylModels.EntitySentiments;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.Sentiment;
import com.google.gson.annotations.Expose;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeckylInterface {

@GET("GetLatestTrendingNews")
    Call<HeckylNews> getNews(

        @Query("asset") String asset,
        @Query("entitytype") String entityType,
        @Query("entitycode") String entityCode,
        @Query("lft") String lft,
        @Query("sortby") String sortBy

        );

    @GET("GetLatestNews")
    Call<HeckylNews> getRegionNews(

            @Query("asset") String asset,
            @Query("lft") String lft,
            @Query("countryid") String countryid


    );

    @GET("GetLatestEntitySentiment")
    Call<EntitySentiments> getEntitySentiments(

            @Query("asset") String sentimentAsset,
            @Query("entitytype") String sentimentEntityType,
            @Query("entitycode") String sentimentEntityCOde

    );


    @GET("GetVideoNews")

    Call<HeckylNews> getVideoNews(

            @Query("lft") String lft

    );

    @GET("GetLatestTrendingNewsForEntities")
    Call<HeckylNews> getNewsForEntities(

            @Query("asset") String asset,
            @Query("entitytype") String entityType,
            @Query("entitycodelist") String entityCodeList,
            @Query("newsCount") String newsCount,
            @Query("lft") String lft,
            @Query("sortby") String sortBy

    );

    @GET("GetSentimentTimeSeries")
    Call<Sentiment> getSentimentTimeeSeries(

           @Query("asset") String asset,
           @Query("entitytype") String entityType,
           @Query("entitycodelist") String entityCodeList




    );


    @GET("GetTrendingCategories")
    Call<CategoriesObject> getCategories(

            @Query("asset") String asset,
            @Query("entitytype ") String entityType,
            @Query("entitycodelist") String entityCodeList,
            @Query("interval") String interval
    );




}
