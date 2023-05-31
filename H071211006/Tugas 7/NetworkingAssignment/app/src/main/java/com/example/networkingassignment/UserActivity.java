package com.example.networkingassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    private ImageView ivAvatar, ivRetry;
    private TextView tvName, tvEmail, tvInternet;
    private ProgressBar progressBar;
    private Handler handler;
    private CardView cardUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ivAvatar = findViewById(R.id.iv_profile);
        tvEmail = findViewById(R.id.tv_email);
        tvName = findViewById(R.id.tv_name);
        tvInternet = findViewById(R.id.tv_internet);
        progressBar = findViewById(R.id.progressBar);
        ivRetry = findViewById(R.id.iv_retry);
        cardUser = findViewById(R.id.card_User);

        handler = new Handler(Looper.getMainLooper());

        getDataUSer();
    }

    private void getDataUSer() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            tvInternet.setVisibility(View.GONE);
            ivRetry.setVisibility(View.GONE);
            cardUser.setVisibility(View.GONE);

            int id = getIntent().getIntExtra(EXTRA_USER, 1);
            Call<UserResponse> client = ApiConfig.getApiService().getIdUser(id);
            client.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    cardUser.setVisibility(View.VISIBLE);

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            UserResponse userResponse = response.body().getDataUser();
                            String fullName = userResponse.getFirstName() + " " + userResponse.getLastName();
                            tvName.setText(fullName);
                            tvEmail.setText(userResponse.getEmail());
                            Glide.with(UserActivity.this).load(userResponse.getAvatarUrl()).into(ivAvatar);
                        }
                    } else {
                        if (response.body() != null) {
                            Toast.makeText(UserActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(UserActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            showRetryButton();
        }
    }
    private void showRetryButton() {
        tvInternet.setVisibility(View.VISIBLE);
        ivRetry.setVisibility(View.VISIBLE);
        cardUser.setVisibility(View.GONE);
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