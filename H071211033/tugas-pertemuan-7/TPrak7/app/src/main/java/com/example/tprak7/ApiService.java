package com.example.tprak7;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<ListDataResponse> getUsers(@Query("page") int page, @Query("per_page") int perPage);

    @GET("users/{id}")
    Call<DataResponse> getUser(@Path("id") int id);
}
