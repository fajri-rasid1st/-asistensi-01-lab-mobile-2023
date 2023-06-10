package com.example.assignment9.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {
    @SerializedName("data")
    private List<User> data;
    public List<User> getData() {
        return data;
    }
}
