package com.t6labs.locals.Common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserProfileDto implements Serializable {

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("profilepicture")
    @Expose
    private String profilePicture;

}
