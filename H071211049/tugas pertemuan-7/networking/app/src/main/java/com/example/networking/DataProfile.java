package com.example.networking;

import com.google.gson.annotations.SerializedName;

public class DataProfile {
    @SerializedName("data")
    private UserResponse data;

    public UserResponse getData() {
        return data;
    }
}
