package com.ez.newsapp.HeckylModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItems {

    @SerializedName("Categorys")
    @Expose
    private List<Categorys> categorys;

    @SerializedName("Channel")
    @Expose
    private String channel;

    @SerializedName("ClusterId")
    @Expose
    private String clusterID;

    @SerializedName("Description")
    @Expose
    private String description;


    @SerializedName("EntityCode")
    @Expose
    private String entityCode;


    @SerializedName("EntityType")
    @Expose
    private String entitiyType;


    @SerializedName("ImageUrl")
    @Expose
    private String imgUrl;


    @SerializedName("IsInTitle")
    @Expose
    private String IsInTitle;


    @SerializedName("LastFetch")
    @Expose
    private String lastFetch;


    @SerializedName("LastFetchTicks")
    @Expose
    private String lastFetchTicks;


    @SerializedName("Link")
    @Expose
    private String link;


    @SerializedName("Name")
    @Expose
    private String name;


    @SerializedName("Sentiment")
    @Expose
    private String sentiment;


    @SerializedName("Source")
    @Expose
    private String source;

    @SerializedName("SourceMapId")
    @Expose
    private String sourceMapId;


    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("TitleCnt")
    @Expose
    private String titleCnt;


    @SerializedName("UniqueGuid")
    @Expose
    private String uniqueGuid;

    public NewsItems(List<Categorys> categorys, String channel, String clusterID, String description, String entityCode, String entitiyType, String imgUrl, String isInTitle, String lastFetch, String lastFetchTicks, String link, String name, String sentiment, String source, String sourceMapId, String title, String titleCnt, String uniqueGuid) {
        this.categorys = categorys;
        this.channel = channel;
        this.clusterID = clusterID;
        this.description = description;
        this.entityCode = entityCode;
        this.entitiyType = entitiyType;
        this.imgUrl = imgUrl;
        IsInTitle = isInTitle;
        this.lastFetch = lastFetch;
        this.lastFetchTicks = lastFetchTicks;
        this.link = link;
        this.name = name;
        this.sentiment = sentiment;
        this.source = source;
        this.sourceMapId = sourceMapId;
        this.title = title;
        this.titleCnt = titleCnt;
        this.uniqueGuid = uniqueGuid;
    }

    public List<Categorys> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Categorys> categorys) {
        this.categorys = categorys;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getEntitiyType() {
        return entitiyType;
    }

    public void setEntitiyType(String entitiyType) {
        this.entitiyType = entitiyType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsInTitle() {
        return IsInTitle;
    }

    public void setIsInTitle(String isInTitle) {
        IsInTitle = isInTitle;
    }

    public String getLastFetch() {
        return lastFetch;
    }

    public void setLastFetch(String lastFetch) {
        this.lastFetch = lastFetch;
    }

    public String getLastFetchTicks() {
        return lastFetchTicks;
    }

    public void setLastFetchTicks(String lastFetchTicks) {
        this.lastFetchTicks = lastFetchTicks;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceMapId() {
        return sourceMapId;
    }

    public void setSourceMapId(String sourceMapId) {
        this.sourceMapId = sourceMapId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleCnt() {
        return titleCnt;
    }

    public void setTitleCnt(String titleCnt) {
        this.titleCnt = titleCnt;
    }

    public String getUniqueGuid() {
        return uniqueGuid;
    }

    public void setUniqueGuid(String uniqueGuid) {
        this.uniqueGuid = uniqueGuid;
    }
}
