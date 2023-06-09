package com.example.tugaspraktikum7.api;

import com.google.gson.annotations.SerializedName;

public class SingleUserDataResponse {
    @SerializedName("data")
    private UserResponse data;
    public UserResponse getData() {
        return data;
    }
}
