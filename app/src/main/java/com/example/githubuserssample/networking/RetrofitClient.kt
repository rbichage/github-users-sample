package com.example.githubuserssample.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
    }


    val apiService: ApiService by lazy {
        retrofitBuilder.build()
                .create(ApiService::class.java)
    }


}