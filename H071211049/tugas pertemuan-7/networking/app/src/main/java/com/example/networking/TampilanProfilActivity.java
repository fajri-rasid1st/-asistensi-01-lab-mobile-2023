package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilanProfilActivity extends AppCompatActivity {
    ImageView avatar;
    TextView name, email;
    ProgressBar pbar;

    public static final String EXTRA_USER = "extra_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_profil);

        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pbar = findViewById(R.id.pbar);

        fetchData();

    }

    public void fetchData() {
        showload();
        int id = getIntent().getIntExtra(EXTRA_USER,1);
        Call<UserResponse> listResponseCall = ApiConfig.getApiService().getIdUser(id);
        listResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        User user = response.body().getData();
                        String fullname = user.getFname() + " " + user.getLname();
                        name.setText(fullname);
                        email.setText(user.getEmail());
                        Glide.with(TampilanProfilActivity.this)
                                .load(user.getPictUrl())
                                .into(avatar);

                    } else {
                        if (response.body() != null) {
                            Log.e("MainActivity", "onFailure: " + response.message());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }

    public void showload() {
        pbar.setVisibility(View.VISIBLE);
    }
}