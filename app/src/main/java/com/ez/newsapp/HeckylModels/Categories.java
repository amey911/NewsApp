package com.ez.newsapp.HeckylModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("CategoryName")
    @Expose

    private String categoryName;

    @SerializedName("Keyword")
    @Expose

    private String keyWord;

    @SerializedName("UniqueGuid")
    @Expose

    private String uniqueGuid;

    @SerializedName("Description")
    @Expose

    private String desc;

    @SerializedName("LastFetch")
    @Expose

    private String lastFetch;

    @SerializedName("Link")
    @Expose

    private String link;

    @SerializedName("Source")
    @Expose

    private String source;

    @SerializedName("Title")
    @Expose

    private String title;

}
