package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import com.example.appquiz.databinding.PlayBinding;
import com.example.appquiz.model.User;

public class PlayActivity extends AppCompatActivity {

    private PlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.actionImage.setImageBitmap(User.image);
        binding.editText1.setText(User.name);
        binding.editText2.setText(String.format("best score : %d", User.maxScore));

        binding.btn1.setOnClickListener(view -> {
            Intent play = new Intent(this, QuestionActivity.class);
            startActivity(play);
        });
    }
}