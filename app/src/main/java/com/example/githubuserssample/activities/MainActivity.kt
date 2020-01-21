package com.example.githubuserssample.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserssample.R
import com.example.githubuserssample.adapters.UsersListAdapter
import com.example.githubuserssample.models.UsersList
import com.example.githubuserssample.networking.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RetrofitClient.apiService.getUsersByLocation("location:nairobi")
                .enqueue(object : Callback<UsersList> {
                    override fun onResponse(call: Call<UsersList>, response: Response<UsersList>) {
                        when (response.code()) {
                            200 -> {

                                progressBar.visibility = View.GONE
                                val usersList = response.body()!!.items
                                val linearLayoutManager = LinearLayoutManager(this@MainActivity)
                                usersRecycler.layoutManager = linearLayoutManager
                                usersRecycler.adapter = UsersListAdapter(usersList!!, this@MainActivity)
                            }
                        }
                    }

                    override fun onFailure(call: Call<UsersList>, t: Throwable) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "Check your connection", Toast.LENGTH_SHORT).show()
                    }
                })
    }
}