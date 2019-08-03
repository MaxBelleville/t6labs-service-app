package com.t6labs.locals.Dtos;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class NewListingRequest {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    public NewListingRequest(@NonNull final String title, @NonNull String description) {

        this.title = title;
        this.description = description;

        //TODO remove hard coded
        this.username = "Test User";

        //TODO add more paramters
    }

}