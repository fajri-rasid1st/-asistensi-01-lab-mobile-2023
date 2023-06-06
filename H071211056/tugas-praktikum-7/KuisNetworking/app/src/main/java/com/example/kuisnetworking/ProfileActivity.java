package com.example.kuisnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressBar = findViewById(R.id.progressBar);
        ImageView avatar = findViewById(R.id.iv_avatar);
        TextView name = findViewById(R.id.tv_name);
        TextView email = findViewById(R.id.tv_email);

        // Retrieve the data from the intent
        String avatarUrl = getIntent().getStringExtra("avatarUrl");
        String fullName = getIntent().getStringExtra("fullName");
        String userEmail = getIntent().getStringExtra("email");

        // Hide the views initially
        avatar.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        email.setVisibility(View.GONE);

        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Set the data to the views after a delay
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Hide the progress bar
            progressBar.setVisibility(View.GONE);

            // Show the views with data
            avatar.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);

            // Set the data to the views
            Glide.with(this)
                    .load(avatarUrl)
                    .into(avatar);
            name.setText(fullName);
            email.setText(userEmail);
        }, 1000);
    }
}
