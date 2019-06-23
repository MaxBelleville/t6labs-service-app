package com.t6labs.locals.services;

import android.support.annotation.NonNull;

import com.t6labs.locals.models.DescriptionDto;
import com.t6labs.locals.models.LocalsDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocalsService {
    @GET("listings")
    Call<ArrayList<LocalsDto>> getLocalsListing();

    @GET("listing/{id}")
    Call<DescriptionDto> getLocalListingDescription(
            @Path("id")
            @NonNull final String id);
}