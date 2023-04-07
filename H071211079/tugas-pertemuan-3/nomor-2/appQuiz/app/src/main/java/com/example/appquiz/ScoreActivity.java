package com.example.appquiz;

import static com.example.appquiz.model.User.name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.appquiz.databinding.ScoreBinding;
import com.example.appquiz.model.User;

public class ScoreActivity extends AppCompatActivity {

    private ScoreBinding binding;

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        binding = ScoreBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        int score = getIntent().getIntExtra("total_score", 0);

        binding.yourScore.setText(String.valueOf(score));

        if (isScoreHighestThanBefore(score)) {
            binding.bestScore.setText(String.valueOf(score));
            User.maxScore = score;
        } else {
            binding.bestScore.setText(String.valueOf(User.maxScore));
        }

        binding.status.setText("GGWP " + name + "!!");
        binding.btnBack.setOnClickListener(view -> setTextOnClick());
    }

    private void setTextOnClick() {

        String name = getIntent().getStringExtra("extra_name");
        String image = getIntent().getStringExtra("PROFILE_IMAGE_URI");

        Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
        intent.putExtra("extra_name", name);
        intent.putExtra("PROFILE_IMAGE_URI", image);
        startActivity(intent);

    }

    private boolean isScoreHighestThanBefore(int score) {
        return score > User.maxScore;
    }
}