package com.example.tprak7;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("data")
    private UserResponse data;
    public UserResponse getData() {
        return data;
    }
}
