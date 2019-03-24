package com.t6labs.locals.services;

import com.t6labs.locals.models.LocalsDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalsService {
    @GET("test")
    Call<ArrayList<LocalsDto>> getLocalsListing();
}