package com.t6labs.locals.services;

import com.t6labs.locals.models.DescriptionDto;
import com.t6labs.locals.models.LocalsDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalsService {
    @GET("tests")
    Call<ArrayList<LocalsDto>> getLocalsListing();

    @GET("description")
    Call<DescriptionDto> getLocalListingDescription();

}