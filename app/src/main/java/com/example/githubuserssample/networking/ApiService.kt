package com.example.githubuserssample.networking

import com.example.githubuserssample.models.UserDetails
import com.example.githubuserssample.models.UsersList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //define your endpoints here
    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String?): UserDetails

    @GET("search/users")
     suspend fun getUsersByLocation(@Query("q") location: String?): UsersList
}