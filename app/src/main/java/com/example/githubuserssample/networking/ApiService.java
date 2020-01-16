package com.example.githubuserssample.networking;

import com.example.githubuserssample.models.UserDetails;
import com.example.githubuserssample.models.UsersList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //define your endpoints here

    @GET("users/{username}")
    Call<UserDetails>  getUserDetails(@Path("username") String username);

    @GET("search/users")
    Call<UsersList> getUsersByLocation(
            @Query("q") String location);
}
