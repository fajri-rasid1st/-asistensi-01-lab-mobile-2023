package com.example.networking_assignment.models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("data")
    private User data;

    public User getData() {
        return data;
    }
}
