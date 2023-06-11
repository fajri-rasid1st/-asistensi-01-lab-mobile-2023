package com.example.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    private User user;

    public User getData() {
        return user;
    }


}
