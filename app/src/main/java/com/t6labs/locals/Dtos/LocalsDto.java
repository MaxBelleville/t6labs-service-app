package com.t6labs.locals.Dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocalsDto implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("rating")
    @Expose
    private Integer rating;

    @SerializedName("distance")
    @Expose
    private Integer distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getUsername() {
        return username;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getDistance() {
        return distance;
    }
}