package com.example.assignment9.api;

import com.example.assignment9.model.UsersResponse;
import com.example.assignment9.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<UsersResponse> getUser(@Query("per_page") String per_page);

    @GET("api/users/{id}")
    Call<UserResponse> getIdUser(@Path("id") int id);
}


