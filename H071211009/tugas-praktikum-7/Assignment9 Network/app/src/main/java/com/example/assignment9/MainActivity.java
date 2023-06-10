package com.example.assignment9;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment9.api.ApiConfig;
import com.example.assignment9.model.User;
import com.example.assignment9.model.UsersResponse;
import com.example.assignment9.model.UserResponse;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ProgressBar progressBar;
    private TextView tvAlert;
    private ImageView ivRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progressBar);
        tvAlert = findViewById(R.id.tvAlert);
        ivRetry = findViewById(R.id.ivRetry);

        ivRetry.setVisibility(View.GONE);
        tvAlert.setVisibility(View.GONE);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try {
                showProgressBar(true);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            handler.post(()->{
                // Memuat data pengguna
                loadData();
            });
        });



        // Mengatur listener untuk tombol retry
        ivRetry.setOnClickListener(v -> {
            showProgressBar(true);

            executor.execute(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                handler.post(()->{
                    // Memuat data pengguna
                    loadData();
                });
            });
        });
    }

    private void loadData() {
        // Memeriksa ketersediaan koneksi internet sebelum memuat data

        // Mengirim permintaan ke API menggunakan Retrofit
        Call<UsersResponse> client = ApiConfig.getApiService().getUser("12");
        client.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // Data pengguna berhasil diterima
                        List<User> userResponses = response.body().getData();
                        adapter = new Adapter(userResponses, MainActivity.this);
                        recyclerView.setAdapter(adapter);

                        showProgressBar(false);
                    }
                } else {
                    // Terjadi kesalahan saat permintaan jaringan
                    Log.e("MainActivity", "onFailure: " + response.message());
                    showConnectionError();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                // Gagal melakukan permintaan jaringan
                Log.e("MainActivity", "onFailure: " + t.getMessage());
                showConnectionError();
            }
        });
    }

    // Menampilkan pesan kesalahan koneksi
    private void showConnectionError() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvAlert.setVisibility(View.VISIBLE);
        ivRetry.setVisibility(View.VISIBLE);
    }

    // Menampilkan progressbar
    private void showProgressBar(boolean value){
        tvAlert.setVisibility(View.GONE);
        ivRetry.setVisibility(View.GONE);
        if (value){
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}
