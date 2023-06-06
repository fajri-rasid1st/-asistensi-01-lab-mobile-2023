package com.example.kuisnetworking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuisnetworking.ApiConfig;
import com.example.kuisnetworking.DataResponse;
import com.example.kuisnetworking.UserAdapter;
import com.example.kuisnetworking.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<UserResponse> usersPage1;
    private List<UserResponse> usersPage2;
    private TextView internetStatus;
    private ImageView internetStatusImg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        internetStatus = findViewById(R.id.tv_network);
        internetStatusImg = findViewById(R.id.iv_network);
        usersPage1 = new ArrayList<>();
        usersPage2 = new ArrayList<>();

        if (isNetworkAvailable()) {
            loadData();
        } else {
            internetStatusImg.setVisibility(View.VISIBLE);
            internetStatus.setVisibility(View.VISIBLE);
            internetStatus.setText("Cek koneksi internet terlebih dahulu");
        }
    }

    private void loadData() {
        Call<DataResponse> clientPage1 = ApiConfig.getApiService().getUser(1);
        clientPage1.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        usersPage1 = response.body().getData();
                        loadUsers();
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });

        Call<DataResponse> clientPage2 = ApiConfig.getApiService().getUser(2);
        clientPage2.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        usersPage2 = response.body().getData();
                        loadUsers();
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadUsers() {
        List<UserResponse> allUsers = new ArrayList<>();
        allUsers.addAll(usersPage1);
        allUsers.addAll(usersPage2);

        userAdapter = new UserAdapter(allUsers, MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(userAdapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
