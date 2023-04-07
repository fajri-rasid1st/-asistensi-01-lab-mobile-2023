package com.example.tprak3_2rev;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {

    private TextView textView1, textView2;
    private Player player;

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        String imageUriStr = getIntent().getStringExtra("imageUriStr");
        player = getIntent().getParcelableExtra("user");
        textView1 = findViewById(R.id.nama);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.btnPlay);

        if (imageUriStr != null && !imageUriStr.isEmpty()) {
            Uri imageUri = Uri.parse(imageUriStr);
            imageView.setImageURI(imageUri);
        }

        if (getIntent().getParcelableExtra("player") != null) {
            player = getIntent().getParcelableExtra("player");

        }

        textView1.setText(player.getNama());

        button.setOnClickListener(view -> play());
    }

    private void play() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", player);
        startActivity(intent);
    }
}