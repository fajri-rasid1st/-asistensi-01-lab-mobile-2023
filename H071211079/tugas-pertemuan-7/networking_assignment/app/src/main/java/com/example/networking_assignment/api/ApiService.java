package com.example.networking_assignment.api;

import com.example.networking_assignment.models.UserResponse;
import com.example.networking_assignment.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<UsersResponse> getListUsers(@Query("page") int page, @Query("per_page") int perPage);

    @GET("api/users/{id}")
    Call<UserResponse> getUser(@Path("id") int id);
}
