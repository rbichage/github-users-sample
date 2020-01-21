package com.example.githubuserssample.repos

import androidx.lifecycle.LiveData
import com.example.githubuserssample.models.UsersList
import com.example.githubuserssample.networking.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object GitHubUserRepo {

    var job: CompletableJob? = null

    fun getUsers(): LiveData<UsersList>{
        return object: LiveData<UsersList>(){
            override fun onActive() {
                super.onActive()
                job = Job()
                
                job?.let {newJob ->
                    
                    CoroutineScope(IO).launch {
                        val userList = RetrofitClient.apiService
                                .getUsersByLocation("location:nairobi")

                        withContext(Main){
                            value = userList
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