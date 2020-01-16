package com.example.githubuserssample.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuserssample.R;
import com.example.githubuserssample.adapters.UsersListAdapter;
import com.example.githubuserssample.models.User;
import com.example.githubuserssample.models.UsersList;
import com.example.githubuserssample.networking.ApiService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.usersRecycler);
        progressBar = findViewById(R.id.progressBar);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getUsersByLocation("location:nairobi")
                .enqueue(new Callback<UsersList>() {
                    @Override
                    public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                        switch (response.code()) {
                            case 200:
                                progressBar.setVisibility(View.GONE);
                                List<User> usersList = response.body().getItems();
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(new UsersListAdapter(usersList, MainActivity.this));

                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersList> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
