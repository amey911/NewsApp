package com.ez.newsapp.HeckylModels;

import com.ez.newsapp.Models.Article;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeckylNews {

    @SerializedName("LFT")
    @Expose
    private String lft;

    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;

    @SerializedName("NewsItems")
    @Expose

    private List<NewsItems> newsItems;


    public String getLft() {
        return lft;
    }

    public void setLft(String lft) {
        this.lft = lft;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<NewsItems> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItems> newsItems) {
        this.newsItems = newsItems;
    }
}
