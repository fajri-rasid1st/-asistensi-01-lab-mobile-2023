package com.example.tugas4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tugas4_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.enter.setOnClickListener(v -> {
            String username = binding.username.getText().toString();

            if(!username.isEmpty()){
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}