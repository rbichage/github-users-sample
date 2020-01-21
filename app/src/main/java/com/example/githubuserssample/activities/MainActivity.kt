package com.example.githubuserssample.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserssample.R
import com.example.githubuserssample.adapters.UsersListAdapter
import com.example.githubuserssample.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userListViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userListViewModel = ViewModelProvider(this).get(UserViewModel::class.java).apply {
            this.getUsers().observe(this@MainActivity, Observer { usersList ->

                val users = usersList.items

                val linearLayoutManager = LinearLayoutManager(this@MainActivity)
                usersRecycler.layoutManager = linearLayoutManager
                usersRecycler.adapter = UsersListAdapter(users!!, this@MainActivity)
                progressBar.visibility = View.GONE
            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        userListViewModel.cancelJobs()
    }
}