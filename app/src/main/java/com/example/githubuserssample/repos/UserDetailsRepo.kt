package com.example.githubuserssample.repos

import androidx.lifecycle.LiveData
import com.example.githubuserssample.models.UserDetails
import com.example.githubuserssample.networking.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object UserDetailsRepo {
    var job: CompletableJob? = null

    fun getUserDetails(username: String): LiveData<UserDetails>{
        job = Job()
        return object: LiveData<UserDetails>(){
            override fun onActive() {
                super.onActive()

                job?.let {newJob ->
                    CoroutineScope(IO + newJob).launch {
                        val userDetails = RetrofitClient.apiService
                                .getUserDetails(username)

                        withContext(Main){
                            value = userDetails
                            newJob.complete()
                        }
                    }
                }

            }

        }
    }

    fun cancelJob(){
        job?.cancel()
    }
}