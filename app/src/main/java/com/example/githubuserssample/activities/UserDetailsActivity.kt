package com.example.githubuserssample.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubuserssample.R
import com.example.githubuserssample.models.User
import com.example.githubuserssample.models.UserDetails
import com.example.githubuserssample.networking.RetrofitClient
import kotlinx.android.synthetic.main.activity_user_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        toolbar.setNavigationOnClickListener { onBackPressed() }


        user = intent.getSerializableExtra("user") as User

        Log.e("USER", user.toString())

        Glide.with(this)
                .load(user.avatarUrl)
                .into(user_avi)

        userDetails

    }
    
    private val userDetails: Unit
    get() {
        RetrofitClient.apiService
                .getUserDetails(user.login)
                .enqueue(object : Callback<UserDetails> {
                    override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                        val userDetails = response.body()

                        user_bio.text = userDetails?.bio
                        user_full_name.text = userDetails?.name
                        user_repos_count.text = userDetails?.publicRepos.toString()
                        user_followers_count.text = userDetails?.followers.toString()
                        user_following_count.text = userDetails?.following.toString()

                    }

                    override fun onFailure(call: Call<UserDetails>, t: Throwable) {

                    }
                })
    }

}