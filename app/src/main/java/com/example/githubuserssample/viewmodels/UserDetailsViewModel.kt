package com.example.githubuserssample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubuserssample.models.UserDetails
import com.example.githubuserssample.repos.UserDetailsRepo

class UserDetailsViewModel : ViewModel() {
    private val _userName: MutableLiveData<String> = MutableLiveData()

    val userDetails: LiveData<UserDetails> = Transformations
            .switchMap(_userName) {
                UserDetailsRepo.getUserDetails(it)
            }

    fun setUserName(userName: String) {
        if (_userName.value == userName) {
            return
        }
        _userName.value = userName
    }

    fun cancelJob() {
        UserDetailsRepo.cancelJob()
    }
}