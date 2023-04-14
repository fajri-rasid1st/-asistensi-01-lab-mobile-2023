package com.example.tugas3_quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class ResultActivity extends AppCompatActivity {

    MaterialCardView home;
    TextView correcttt, wrongttt, resultinfo, resultscore, resultUsername;
    ImageView resultImage, resultProfile;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        home = findViewById(R.id.returnHome);
        correcttt = findViewById(R.id.correctScore);
        wrongttt = findViewById(R.id.wrongScore);
        resultinfo = findViewById(R.id.resultInfo);
        resultscore = findViewById(R.id.resultScore);
        resultImage = findViewById(R.id.resultImage);
        resultProfile = findViewById(R.id.resultProfile);
        resultUsername = findViewById(R.id.resultUsername);


        int correct = getIntent().getIntExtra("correct", 0);
        int wrong = getIntent().getIntExtra("wrong", 0);
        int score = correct * 5;


        correcttt.setText("" + correct);
        wrongttt.setText("" + wrong);
        resultscore.setText("" + score);
        resultProfile.setImageURI(Uri.parse(getIntent().getStringExtra("profilePict")));
        String userName = getIntent().getStringExtra("username");

        if (userName != null) {
            resultUsername.setText(userName);
            System.out.println(userName);
        }


        if (correct >= 0 && correct <= 2) {
            resultinfo.setText("You have to take the test again");
            resultImage.setImageResource(R.drawable.ic_sad);
        } else if (correct >= 3 && correct <= 5) {
            resultinfo.setText("You have to try a little more");
            resultImage.setImageResource(R.drawable.ic_neutral);
        } else if (correct >= 6 && correct <= 8) {
            resultinfo.setText("You are pretty good");
            resultImage.setImageResource(R.drawable.ic_smile);
        } else {
            resultinfo.setText("You are very good congratulations");
            resultImage.setImageResource(R.drawable.ic_smile);
        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
