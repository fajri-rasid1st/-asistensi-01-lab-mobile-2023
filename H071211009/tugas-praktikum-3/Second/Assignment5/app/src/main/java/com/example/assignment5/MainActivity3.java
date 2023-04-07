package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment5.model.User;

public class MainActivity3 extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    public static final String EXTRA_SCORE = "extra_score";
    private TextView tvName, tvScore, tvBestScore;
    private Button btnBackHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvName = findViewById(R.id.name);
        tvScore = findViewById(R.id.score);
        tvBestScore = findViewById(R.id.tvBestScore);
        btnBackHome = findViewById(R.id.btnBackHome);

        User user = getIntent().getParcelableExtra(EXTRA_USER);
        tvName.setText(user.getName());
        int score = getIntent().getIntExtra(EXTRA_SCORE, 0);
        tvScore.setText(String.valueOf(score));

        if (score > user.getScore()){
            tvBestScore.setText(String.valueOf(score));
        }else{
            user.setScore(score);
            tvBestScore.setText(String.valueOf(user.getScore()));
        }

        //btn back home
        btnBackHome.setOnClickListener(view -> {
            setResult(RESULT_OK, new Intent());
            finish();
        });
    }
}