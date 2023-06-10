package com.example.assignment9.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("data")
    private User data;
    public User getDataUser() {
        return data;
    }
}
