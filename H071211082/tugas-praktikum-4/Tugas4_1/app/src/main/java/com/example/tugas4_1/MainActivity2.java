package com.example.tugas4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.tugas4_1.databinding.ActivityMain2Binding;
import com.example.tugas4_1.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private String username;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        boolean ulang = false;
        boolean ulangan = getIntent().getBooleanExtra("ulang", false);

        if(ulang != ulangan){
            username = getIntent().getStringExtra("username2");
            binding.username2.setText(username);

            String bestScore = getIntent().getStringExtra("bestScore");
            binding.bestScore.setText(bestScore);
        }
        else {
            username = getIntent().getStringExtra("username");
            binding.username2.setText(username);
        }
        setListeners();
    }

    private void setListeners(){
        binding.play.setOnClickListener(v -> {
            String bestScore = binding.bestScore.getText().toString();
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("username", username);
            intent.putExtra("bestScore", bestScore);
            startActivity(intent);
        });
    }
}