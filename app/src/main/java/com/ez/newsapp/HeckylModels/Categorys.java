package com.ez.newsapp.HeckylModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categorys {

    @SerializedName("CategoryName")
    @Expose
    private String categoryName;

    @SerializedName("Keyword")
    @Expose
    private String keyword;

    @SerializedName("UniqueGuid")
    @Expose
    private String uniqueGuid;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUniqueGuid() {
        return uniqueGuid;
    }

    public void setUniqueGuid(String uniqueGuid) {
        this.uniqueGuid = uniqueGuid;
    }
}
