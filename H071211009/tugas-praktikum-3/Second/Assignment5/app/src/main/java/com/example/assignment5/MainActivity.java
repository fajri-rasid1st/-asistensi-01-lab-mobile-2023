package com.example.assignment5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment5.model.User;
import com.google.android.material.color.utilities.Score;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etName;

    private TextInputLayout tilName;

    private TextView tvShowName,tvBestScore, tvScore;
    private ImageView ivProfilePict;

    private Button btnApply, btnPlay;
    private Uri uri;

    private User user;

    private final ActivityResultLauncher<Intent> pickImg = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK){
            Intent intent = result.getData();
            if (intent != null){
                uri = intent.getData();
                ivProfilePict.setImageURI(Uri.parse(uri.toString()));
            }
        }
    });

    //untuk ambil score
    private final ActivityResultLauncher<Intent> gameResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK){
            Intent intent = result.getData();
            if (intent != null){
                int score = intent.getIntExtra(MainActivity3.EXTRA_SCORE, 0);
                // untuk cek score yg dikirim apakah lbh tinggi dibandingkan score skrg
                if (user.getScore() < score){
                    user.setScore(score);
                    tvScore.setText(String.valueOf(score));
                }
            }
        }
    });

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        ivProfilePict = (ImageView) findViewById(R.id.ivProfilePict);

        tvShowName = findViewById(R.id.tvShowName);
        tvBestScore = findViewById(R.id.tvBestScore);
        tvScore = findViewById(R.id.tvScore);

        tilName = findViewById(R.id.tilName);
        btnApply = findViewById(R.id.btnApply);
        btnPlay = findViewById(R.id.btnPlay);

        uri = Uri.parse("");

        etName.setVisibility(View.VISIBLE);
        ivProfilePict.setVisibility(View.VISIBLE);
        btnApply.setVisibility(View.VISIBLE);
        btnPlay.setVisibility(View.GONE);
        tvShowName.setVisibility(View.GONE);
        tvBestScore.setVisibility(View.GONE);
        tvScore.setVisibility(View.GONE);

        user = new User();
    }

    public void apply(View view) {
        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Field can't be empty");
        } else {
            etName.setVisibility(View.GONE);
            btnApply.setVisibility(View.GONE);
            tilName.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
            ivProfilePict.setVisibility(View.VISIBLE);
            tvShowName.setVisibility(View.VISIBLE);
            tvBestScore.setVisibility(View.VISIBLE);
            tvScore.setVisibility(View.VISIBLE);

            user.setName(name);
            tvShowName.setText(user.getName());
        }
    }

    public void play(View view){
        if (user.getName() != null){
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra(MainActivity2.EXTRA_USER, user);

            gameResult.launch(intent);
        }
    }

    public void image(View view) {
        Intent image = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImg.launch(Intent.createChooser(
                image, "Pick a photo"
        ));
    }
}