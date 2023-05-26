package com.example.tprak7;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDataResponse {
    @SerializedName("data")
    private List<UserResponse> data;
    public List<UserResponse> getData() {
        return data;
    }
}
