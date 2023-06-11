package com.example.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse {
    @SerializedName("data")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }


}
