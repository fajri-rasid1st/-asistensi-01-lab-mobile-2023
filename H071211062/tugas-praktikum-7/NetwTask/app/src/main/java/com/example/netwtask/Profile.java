package com.example.netwtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        CircleImageView avatar = findViewById(R.id.profileImageView);
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);


        String avatarUrl = getIntent().getStringExtra("avatarUrl");
        String fullName = getIntent().getStringExtra("fullName");
        String userEmail = getIntent().getStringExtra("email");


        Glide.with(this)
                .load(avatarUrl)
                .into(avatar);
        name.setText(fullName);
        email.setText(userEmail);

    }
}