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

    private int Freq;

    @SerializedName("Negative")
    @Expose

    private int Negative;


    @SerializedName("Neutral")
    @Expose

    private int neutral;


    @SerializedName("Positive")
    @Expose

    private int positive;

    @SerializedName("PositiveNegative")
    @Expose

    private int positiveNegative;


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

    public int getFreq() {
        return Freq;
    }

    public void setFreq(int freq) {
        Freq = freq;
    }

    public int getNegative() {
        return Negative;
    }

    public void setNegative(int negative) {
        Negative = negative;
    }

    public int getNeutral() {
        return neutral;
    }

    public void setNeutral(int neutral) {
        this.neutral = neutral;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getPositiveNegative() {
        return positiveNegative;
    }

    public void setPositiveNegative(int positiveNegative) {
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
