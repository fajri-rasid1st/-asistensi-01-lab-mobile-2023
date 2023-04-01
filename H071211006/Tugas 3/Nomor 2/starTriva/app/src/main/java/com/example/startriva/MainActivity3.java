package com.example.startriva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extra_score";

    private TextView jumlahScore, jumlahBestScore;

    private Button backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        jumlahScore = findViewById(R.id.tv_jumlahScore);
        jumlahBestScore = findViewById(R.id.tv_jumlahBestScore);
        backToHome = findViewById(R.id.btn_backToHome);

        int Score = getIntent().getIntExtra(EXTRA_SCORE, 0);
        Player player = getIntent().getParcelableExtra(MainActivity2.EXTRA_PLAYER);

        jumlahScore.setText(String.valueOf(Score));

        if (Score > player.getBestScore()) {
            player.setBestScore(Score);
            jumlahBestScore.setText(String.valueOf(player.getBestScore()));
        } else {
            jumlahBestScore.setText(String.valueOf(player.getBestScore()));
        }

        backToHome.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("player", player);
            startActivity(intent);

        });
    }
}