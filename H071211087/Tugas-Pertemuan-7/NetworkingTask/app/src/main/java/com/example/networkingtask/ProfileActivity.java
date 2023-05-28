package com.example.networkingtask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private TextView nameView;
    private TextView emailView;
    private ImageView profileImageView;
    private TextView textView;
    private Button button;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        int id = getIntent().getIntExtra("id",0);
        Call<DataResponse> client = ApiConfig.getApiService().getUser(id);

        Handler handler = new Handler();
        progressBar = findViewById(R.id.progressBar);

        textView = findViewById(R.id.txtloss);
        button = findViewById(R.id.btnloss);
        nameView = findViewById(R.id.nameView);
        emailView = findViewById(R.id.emailView);
        profileImageView = findViewById(R.id.profileImageView);

        profileImageView.setVisibility(View.GONE);
        nameView.setVisibility(View.GONE);
        emailView.setVisibility(View.GONE);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                profileImageView.setVisibility(View.VISIBLE);
                nameView.setVisibility(View.VISIBLE);
                emailView.setVisibility(View.VISIBLE);

                client.enqueue(new Callback<DataResponse>() {

                    @Override
                    public void onResponse(Call<DataResponse> call, Response<DataResponse>
                            response) {

                        if (response.isSuccessful()) {

                            if (response.body() != null) {
                                UserResponse userResponse = response.body().getData();
                                nameView.setText(userResponse.getFirstName()+ " " + userResponse.getLastName());
                                emailView.setText(userResponse.getEmail());
                                Glide.with(ProfileActivity.this)
                                        .load(userResponse.getAvatarUrl())
                                        .into(profileImageView);

                            }
                        } else {
                            if (response.body() != null) {

                                Log.e("MainActivity", "onFailure: " + response.message());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<DataResponse> call, Throwable t) {

                        profileImageView.setVisibility(View.GONE);
                        nameView.setVisibility(View.GONE);
                        emailView.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(view -> recreate());

                    }
                });

            }
        },2000);



    }
}