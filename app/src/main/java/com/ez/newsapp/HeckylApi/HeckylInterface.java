package com.ez.newsapp.HeckylApi;

import com.ez.newsapp.HeckylModels.HeckylNews;

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


}
