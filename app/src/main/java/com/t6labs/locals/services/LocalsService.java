package com.t6labs.locals.services;


import androidx.annotation.NonNull;

import com.t6labs.locals.Common.DescriptionDto;
import com.t6labs.locals.Common.LocalsDto;
import com.t6labs.locals.Common.LoginRequest;
import com.t6labs.locals.Common.NewListingRequest;
import com.t6labs.locals.Common.UserProfileDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LocalsService {

    @GET("listings")
    Call<ArrayList<LocalsDto>> getLocalsListing();

    @GET("listing/{id}")
    Call<DescriptionDto> getLocalListingDescription(
            @Path("id")
            @NonNull final String id);

    @POST("listing")
    Call<DescriptionDto> postLocalListing(@Body @NonNull NewListingRequest request);

    @POST("login")
    Call<UserProfileDto> login(
            @Header("token") String token,
            @Body @NonNull LoginRequest request
    );

}