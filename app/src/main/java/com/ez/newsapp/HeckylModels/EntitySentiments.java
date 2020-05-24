package com.ez.newsapp.HeckylModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntitySentiments {

    @SerializedName("EntityCode")
    @Expose

    private String entitiyCode;

    @SerializedName("EntityType")
    @Expose

    private String entityType;

    @SerializedName("Frequency")
    @Expose

    private String Freq;

    @SerializedName("Negative")
    @Expose

    private String Negative;


    @SerializedName("Neutral")
    @Expose

    private String neutral;


    @SerializedName("Positive")
    @Expose

    private String positive;

    @SerializedName("PositiveNegative")
    @Expose

    private String positiveNegative;


    @SerializedName("PubDate")
    @Expose

    private String pubDate;


    @SerializedName("Symbol")
    @Expose

    private String symbol;

    public String getEntitiyCode() {
        return entitiyCode;
    }

    public void setEntitiyCode(String entitiyCode) {
        this.entitiyCode = entitiyCode;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getFreq() {
        return Freq;
    }

    public void setFreq(String freq) {
        Freq = freq;
    }

    public String getNegative() {
        return Negative;
    }

    public void setNegative(String negative) {
        Negative = negative;
    }

    public String getNeutral() {
        return neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getPositiveNegative() {
        return positiveNegative;
    }

    public void setPositiveNegative(String positiveNegative) {
        this.positiveNegative = positiveNegative;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
