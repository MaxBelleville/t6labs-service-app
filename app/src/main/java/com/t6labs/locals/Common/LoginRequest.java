package com.t6labs.locals.Common;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class LoginRequest {

    @SerializedName("type")
    @Expose
    private String type;

    public LoginRequest (@NonNull final String type) {
        this.type = type;
    }

}