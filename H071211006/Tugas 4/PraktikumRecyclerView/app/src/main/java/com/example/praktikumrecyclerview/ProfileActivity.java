package com.example.praktikumrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName;
    ImageView ivPhoto, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tv_name);
        ivPhoto = findViewById(R.id.iv_photo);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        String name = intent.getStringExtra("varName");
        String photo = intent.getStringExtra("varPhoto");

        tvName.setText(name);

        Glide.with(ProfileActivity.this)
                .load(photo)
                .into(ivPhoto);

        back.setOnClickListener(view -> {
            finish();
        });
    }
}