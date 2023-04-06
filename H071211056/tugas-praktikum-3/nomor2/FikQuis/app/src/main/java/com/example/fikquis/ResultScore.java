package com.example.fikquis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultScore extends AppCompatActivity {
    TextView newScore, oldScore, name;
    Button restart;
    String iName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_score);

        newScore = findViewById(R.id.newScore);
        oldScore = findViewById(R.id.oldScore);
        name = findViewById(R.id.name);
        restart = findViewById(R.id.restart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Questions2.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        newScore.setText("" + score);
        iName = intent.getStringExtra("inputName");
        name.setText(iName);

        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore", 0);
        if (highscore >= score){
            oldScore.setText("High Score :" + highscore);
        }else {
            oldScore.setText("New High Score :" + score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }
    }
}



