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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.assignment9.api.ApiConfig;
import com.example.assignment9.model.User;
import com.example.assignment9.model.UserResponse;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    private ImageView ivProfilePic, ivRetry;
    private TextView tvFullName, tvEmail, tvAlert;
    private ProgressBar progressBar;
    private CardView cardUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivProfilePic = findViewById(R.id.foto_profil);
        tvFullName = findViewById(R.id.tvFullname);
        tvEmail = findViewById(R.id.tvEmail);
        tvAlert = findViewById(R.id.tvAlert);
        ivRetry = findViewById(R.id.ivRetry);
        progressBar = findViewById(R.id.progressBar);
        cardUserProfile = findViewById(R.id.cardUserProfile);

        // Mendapatkan ID pengguna dari intent
        int id = getIntent().getIntExtra(EXTRA_USER, 1);

        // Cek ketersediaan koneksi internet sebelum membuat permintaan jaringan
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
                loadData(id);
            });
        });

        // Klik retry
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
                    loadData(id);
                });
            });
        });
    }


    // Memuat data pengguna dari API menggunakan Retrofit
    private void loadData(int id) {
        Call<UserResponse> client = ApiConfig.getApiService().getIdUser(id);
        client.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // Data pengguna berhasil diterima

                        User userResponse = response.body().getDataUser();
                        Glide.with(UserProfileActivity.this)
                                .load(userResponse.getAvatarUrl())
                                .into(ivProfilePic);
                        String fullName = userResponse.getFirstName() + " " +
                                userResponse.getLastName();
                        tvFullName.setText(fullName);
                        tvEmail.setText(userResponse.getEmail());

                        showProgressBar(false);
                    }
                } else {
                    // Terjadi kesalahan saat permintaan jaringan
                    Log.e("UserProfileActivity", "onFailure: " + response.message());
                    showConnectionError();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                showConnectionError();
                Toast.makeText(UserProfileActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                Log.e("UserProfileActivity", "onFailure: " + t.getMessage());
            }
        });
    }

    // Menampilkan pesan kesalahan koneksi
    private void showConnectionError() {
        progressBar.setVisibility(View.GONE);
        cardUserProfile.setVisibility(View.GONE);
        tvAlert.setVisibility(View.VISIBLE);
        ivRetry.setVisibility(View.VISIBLE);
    }

    // Menampilkan progressbar
    private void showProgressBar(boolean value) {
        tvAlert.setVisibility(View.GONE);
        ivRetry.setVisibility(View.GONE);
        if (value) {
            progressBar.setVisibility(View.VISIBLE);
            cardUserProfile.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            cardUserProfile.setVisibility(View.VISIBLE);
        }
    }
}