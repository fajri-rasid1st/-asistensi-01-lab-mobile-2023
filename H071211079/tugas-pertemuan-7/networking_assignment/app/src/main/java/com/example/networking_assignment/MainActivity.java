package com.example.networking_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.networking_assignment.api.ApiConfig;
import com.example.networking_assignment.models.User;
import com.example.networking_assignment.models.UsersResponse;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView rvUser;
    private ImageView ivNoInternet;
    private TextView tvNoInternet;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        rvUser = findViewById(R.id.recyclerView);
        ivNoInternet = findViewById(R.id.iv_no_internet);
        tvNoInternet = findViewById(R.id.tv_no_internet);

        rvUser.setLayoutManager(new LinearLayoutManager(this));

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            showProgressBar(true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            handler.post(() -> fetchApi());
        });

        ivNoInternet.setOnClickListener(v -> {
            showProgressBar(true);

            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                handler.post(() -> fetchApi());
            });
        });
    }

    private void fetchApi() {
        ApiConfig.getApiService().getListUsers(1, 12).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    final List<User> users = response.body().getData();
                    userAdapter = new UserAdapter(users);
                    rvUser.setAdapter(userAdapter);

                    showProgressBar(false);
                } else {
                    showNoInternet();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                showNoInternet();
            }
        });
    }

    private void showProgressBar(boolean isLoading) {
        tvNoInternet.setVisibility(View.GONE);
        ivNoInternet.setVisibility(View.GONE);
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            rvUser.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            rvUser.setVisibility(View.VISIBLE);
        }
    }

    private void showNoInternet() {
        tvNoInternet.setVisibility(View.VISIBLE);
        ivNoInternet.setVisibility(View.VISIBLE);
        rvUser.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
}