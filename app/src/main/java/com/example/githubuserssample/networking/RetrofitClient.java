package com.example.githubuserssample.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Retrofit retrofit;
    private final String BASE_URL = "https://api.github.com/";

    public RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }

        return instance;
    }

    public ApiService getApi() {
        return retrofit.create(ApiService.class);
    }
}
