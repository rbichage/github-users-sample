package com.example.githubuserssample.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.githubuserssample.R;
import com.example.githubuserssample.models.User;
import com.example.githubuserssample.models.UserDetails;
import com.example.githubuserssample.networking.RetrofitClient;
import com.google.android.material.appbar.MaterialToolbar;
import com.mikhaellopez.circularimageview.CircularImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {
    TextView fullName, bio, repos, followers, following;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        MaterialToolbar materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);
        materialToolbar.setNavigationOnClickListener(v -> onBackPressed());

        fullName = findViewById(R.id.user_full_name);
        bio = findViewById(R.id.user_bio);
        repos = findViewById(R.id.user_repos_count);
        followers = findViewById(R.id.user_followers_count);
        following = findViewById(R.id.user_following_count);

        User user = (User) getIntent().getSerializableExtra("user");
        Log.e("USER", user.toString());
        CircularImageView userAvi = findViewById(R.id.user_avi);

        Glide.with(this)
                .load(user.getAvatarUrl())
                .into(userAvi);

        getUserDetails(user.getLogin());


    }

    private void getUserDetails(String login) {
        RetrofitClient.getInstance().getApi()
                .getUserDetails(login)
                .enqueue(new Callback<UserDetails>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                        if (response.code()==200){
                            UserDetails userDetails = response.body();

                            fullName.setText(userDetails.getName());
                            bio.setText(userDetails.getBio());
                            repos.setText(userDetails.getPublicRepos().toString());
                            followers.setText(userDetails.getFollowers().toString());
                            following.setText(userDetails.getFollowing().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetails> call, Throwable t) {

                    }
                });
    }


}
