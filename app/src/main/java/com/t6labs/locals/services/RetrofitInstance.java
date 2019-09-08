package com.t6labs.locals.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    // TODO: 2019-09-08 get BASE_URL from enpoints xml using mock vs relase flavours
    private static final String BASE_URL = "http://t6-api.azurewebsites.net/";
    //private static final String BASE_URL = "http://private-a4fc5e-t6labs.apiary-mock.com/";
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit getRetrofitInstance() {

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }
}