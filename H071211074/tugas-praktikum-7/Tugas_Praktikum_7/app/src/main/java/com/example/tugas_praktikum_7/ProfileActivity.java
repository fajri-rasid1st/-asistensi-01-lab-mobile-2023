package com.example.tugas_praktikum_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugas_praktikum_7.api.ApiConfig;
import com.example.tugas_praktikum_7.models.User;
import com.example.tugas_praktikum_7.models.UserResponse;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_USER = "extra_user";

    private ImageView ivNoInternet;
    private TextView tvNoInternet;
    private ProgressBar progressBar;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivNoInternet = findViewById(R.id.iv_no_internet);
        tvNoInternet = findViewById(R.id.tv_no_internet);
        progressBar = findViewById(R.id.progress_bar);
        cardView = findViewById(R.id.card_view);

        int id = getIntent().getIntExtra(EXTRA_USER, 0);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            showProgressBar(true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            handler.post(() -> fetchApi(id));
        });

        ivNoInternet.setOnClickListener(v -> {
            showProgressBar(true);

            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                handler.post(() -> fetchApi(id));
            });
        });
    }

    private void fetchApi(int id) {
        ApiConfig.getApiService().getUser(id).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    final User user = response.body().getData();
                    setUser(user);

                    showProgressBar(false);
                } else {
                    showNoInternet();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                showNoInternet();
            }
        });
    }

    private void setUser(User user) {
        ShapeableImageView ivProfile = findViewById(R.id.iv_profile);
        TextView tvFullname = findViewById(R.id.tv_name);
        TextView tvEmail = findViewById(R.id.tv_email);

        Glide.with(this).load(user.getAvatar()).into(ivProfile);
        tvFullname.setText(user.getFullname());
        tvEmail.setText(user.getEmail());
    }

    private void showProgressBar(boolean isLoading) {
        tvNoInternet.setVisibility(View.GONE);
        ivNoInternet.setVisibility(View.GONE);
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);
        }
    }

    private void showNoInternet() {
        tvNoInternet.setVisibility(View.VISIBLE);
        ivNoInternet.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
}