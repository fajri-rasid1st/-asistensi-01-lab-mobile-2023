package com.example.tugaspraktikum6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tugaspraktikum6.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        User user = getIntent().getParcelableExtra("data");
        binding.tvUsername.setText(user.getUsername());
        binding.tvNickname.setText(user.getNickname());
        binding.ivProfile.setImageURI(user.getImageUri());

    }
}