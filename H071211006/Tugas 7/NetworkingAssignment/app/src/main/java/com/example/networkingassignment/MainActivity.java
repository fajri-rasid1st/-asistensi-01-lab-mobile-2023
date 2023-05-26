package com.example.networkingassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUser;
    private ImageView ivRetry;
    private TextView tvInternet;
    private ProgressBar progressBar;
    private Handler handler;
    LinearLayoutManager layoutManager;
    List<UserResponse> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInternet = findViewById(R.id.tv_internet);
        progressBar = findViewById(R.id.progressBar);
        ivRetry = findViewById(R.id.iv_retry);

        handler = new Handler(Looper.getMainLooper());

        rvUser = findViewById(R.id.rv_user);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        rvUser.setHasFixedSize(true);

        getDataUSer();

    }

    private void getDataUSer() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            rvUser.setVisibility(View.GONE);
            tvInternet.setVisibility(View.GONE);
            ivRetry.setVisibility(View.GONE);

            Call<DataResponse> client = ApiConfig.getApiService().getUser(1, 12);

            client.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    rvUser.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            listUser.addAll(response.body().getData());

                        }
                    } else {
                        if (response.body() != null) {
                            Toast.makeText(MainActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });

            UserAdapter userAdapter = new UserAdapter(MainActivity.this, listUser);
            rvUser.setLayoutManager(layoutManager);
            rvUser.setAdapter(userAdapter);

        } else {
           showRetryButton();
        }

    }

    private void showRetryButton() {
        tvInternet.setVisibility(View.VISIBLE);
        ivRetry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        ivRetry.setOnClickListener(view -> {
            tvInternet.setVisibility(View.GONE);
            ivRetry.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            handler.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                getDataUSer();
            }, 500);

        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}