package com.ez.newsapp.HeckylModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesObject {

    @SerializedName("Categories")
    @Expose

    List<Categories> categories;

    @SerializedName("EntityCode")
    @Expose

    private String entityCode;

    @SerializedName("EntityType")
    @Expose

    private String entityType;

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
