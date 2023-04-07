package com.example.startriva;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView name, score;
    private ImageView profile;
    private EditText et_name;
    private Button apply, play;
    private Uri uri;
    private Player player;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent resultIntent = result.getData();
                    if (resultIntent != null) {
                        uri = resultIntent.getData();
                        player.setProfileImg(uri);
                        profile.setImageURI(uri);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.tv_name);
        score = findViewById(R.id.tv_score);
        profile = findViewById(R.id.img_profile);
        et_name = findViewById(R.id.et_name);
        apply = findViewById(R.id.btn_apply);
        play = findViewById(R.id.btn_play);

        et_name.setVisibility(View.VISIBLE);
        profile.setVisibility(View.VISIBLE);
        apply.setVisibility(View.VISIBLE);
        name.setVisibility(View.GONE);
        score.setVisibility(View.GONE);
        play.setVisibility(View.GONE);

        if (getIntent().getParcelableExtra("player") != null) {
            player = getIntent().getParcelableExtra("player");
            et_name.setVisibility(View.GONE);
            profile.setVisibility(View.VISIBLE);
            apply.setVisibility(View.GONE);
            name.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            play.setVisibility(View.VISIBLE);

            if (player.getProfileImg() != null) {
                profile.setImageURI(player.getProfileImg());
            }

            String Names = player.getName();
            name.setText(Names);
            int Score = player.getBestScore();
            score.setText("Best Score : " + Score);
        } else {
            player = new Player();
        }

        apply.setOnClickListener(view -> {
            if (et_name.getText().toString().isEmpty()) {
                et_name.setError("Please input your name first!");
            } else {
                et_name.setVisibility(View.GONE);
                profile.setVisibility(View.VISIBLE);
                apply.setVisibility(View.GONE);
                name.setVisibility(View.VISIBLE);
                score.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);

                player.setName(et_name.getText().toString());
                String Names = player.getName();
                name.setText(Names);
                int Score = player.getBestScore();
                score.setText("Best Score : " + Score);
            }
        });

        play.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra(MainActivity2.EXTRA_PLAYER, player);

            startActivity(intent);
        });

        profile.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(Intent.createChooser(intent, "Pilih Photo Profil"));
        });
    }
}