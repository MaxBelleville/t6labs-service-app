package com.t6labs.locals.services;


import androidx.annotation.NonNull;

import com.t6labs.locals.Dtos.DescriptionDto;
import com.t6labs.locals.Dtos.LocalsDto;
import com.t6labs.locals.Dtos.NewListingRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

}