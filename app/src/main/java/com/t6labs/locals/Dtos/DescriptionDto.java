package com.t6labs.locals.Dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DescriptionDto implements Serializable {

    public DescriptionDto(String title,String description){
        this.title=title;
        this.description=description;
        this.username="Test User";
    }

    public String getId() {
        return id;
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

    public String getRating() {
        return rating;
    }

    public String getDistance() {
        return distance;
    }


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
    private String rating;

    @SerializedName("distance")
    @Expose
    private String distance;

}