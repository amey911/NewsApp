package com.ez.newsapp.HeckylModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sentiment {

    @SerializedName("EntitySentiments")
    @Expose
    List<EntitySentiments> entitySentiments;

    public List<EntitySentiments> getEntitySentiments() {
        return entitySentiments;
    }

    public void setEntitySentiments(List<EntitySentiments> entitySentiments) {
        this.entitySentiments = entitySentiments;
    }
}
