package com.example.githubuserssample.models;


import com.example.githubuserssample.networking.RetrofitClient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersList {

    @SerializedName("items")
    @Expose
    private List<User> items = null;

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }


}




