package com.example.githubuserssample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UsersList {
    @SerializedName("items")
    @Expose
    var items: List<User>? = null

}