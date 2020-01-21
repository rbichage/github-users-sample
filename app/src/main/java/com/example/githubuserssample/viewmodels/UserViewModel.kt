package com.example.githubuserssample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserssample.models.UsersList
import com.example.githubuserssample.repos.GitHubUserRepo

class UserViewModel: ViewModel() {

    fun getUsers(): LiveData<UsersList>{
        return GitHubUserRepo.getUsers()
    }

    fun cancelJobs(){
        GitHubUserRepo.cancelJob()
    }

}