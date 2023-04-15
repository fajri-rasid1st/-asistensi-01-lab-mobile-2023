package com.example.tugas4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tugas4_1.databinding.ActivityMain3Binding;
import com.example.tugas4_1.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {

    private ActivityMain4Binding binding;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int currentScore = getIntent().getIntExtra("score", 0);
        binding.score.setText(String.valueOf(currentScore));
        System.out.println(binding.score);

        int highScore =  Integer.parseInt(binding.bestScore2.getText().toString());

        int bestScore = Integer.parseInt(getIntent().getStringExtra("bestScore"));

        if(currentScore > highScore ){
            highScore = currentScore;
            binding.bestScore2.setText(String.valueOf(highScore));
            if(highScore > bestScore){
                binding.bestScore2.setText(String.valueOf(highScore));
            }
            else {
                binding.bestScore2.setText(String.valueOf(bestScore));
            }
        }
        else {
//            binding.bestScore2.setText(String.valueOf(highScore));
            binding.bestScore2.setText(String.valueOf(bestScore));
        }

        binding.retry.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            username = getIntent().getStringExtra("username");
            intent.putExtra("bestScore", binding.bestScore2.getText());
            intent.putExtra("username2", username);
            intent.putExtra("ulang", true);
            startActivity(intent);
        });
    }
}