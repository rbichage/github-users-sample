package com.example.githubuserssample.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserssample.R
import com.example.githubuserssample.models.User
import com.example.githubuserssample.viewmodels.UserDetailsViewModel
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var user: User
    lateinit var userDetailsViewModel: UserDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        setSupportActionBar(toolbar)


        user = intent.getSerializableExtra("user") as User
        toolbar.setNavigationOnClickListener { onBackPressed() }


        userDetailsViewModel = ViewModelProvider(this).get(UserDetailsViewModel::class.java).apply {

            this.setUserName(user.login)

            this.userDetails.observe(this@UserDetailsActivity, Observer {userDetails ->
                user_bio.text = userDetails?.bio
                user_full_name.text = userDetails?.name
                user_repos_count.text = userDetails?.publicRepos.toString()
                user_followers_count.text = userDetails?.followers.toString()
                user_following_count.text = userDetails?.following.toString()
            })
        }

        Glide.with(this)
                .load(user.avatarUrl)
                .into(user_avi)


    }

    override fun onDestroy() {
        super.onDestroy()
        userDetailsViewModel.cancelJob()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {

            val messageBody = "Check out this awesome developer @${user.login}. \n\n ${user.htmlUrl}."

            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, messageBody)
                type = "text/plain"
            }

            startActivity(Intent.createChooser(intent, null))

        }

        return super.onOptionsItemSelected(item)

    }



}