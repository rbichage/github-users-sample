package com.example.githubuserssample.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private RetrofitService instance = null;
    private Retrofit retrofit;
    private final String BASE_URL = "https://api.github.com/";

    public RetrofitService() {
        buildRetrofit(BASE_URL);
    }

    private void buildRetrofit(String baseUrl) {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }

        return instance;
    }

    public ApiClient getApi() {
        return retrofit.create(ApiClient.class);
    }
}
