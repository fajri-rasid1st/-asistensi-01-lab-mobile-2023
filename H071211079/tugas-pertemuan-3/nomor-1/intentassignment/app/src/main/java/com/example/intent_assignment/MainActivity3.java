package com.example.intent_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.intent_assignment.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain3Binding binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.photoProfile.setImageBitmap(User.image);
        binding.textUsername.setText(User.username);
        binding.textFullname.setText(User.name);

        binding.photoUpload.setImageBitmap(Post.photo);
        binding.caption.setText(Post.deskripsi);
    }
}