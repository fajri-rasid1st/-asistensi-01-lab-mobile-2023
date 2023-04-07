package com.example.tprak3_2rev;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NamaActivity extends AppCompatActivity {

    private Button button;
    private Uri imageUri;
    private ImageView imageView;
    private TextInputEditText textInputEditText;
    private Player player;

    private final ActivityResultLauncher<String> getContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri uri) {
            if (uri != null) {
                imageUri = uri;
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageURI(uri);

                player.setImage(imageUri);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama);

        imageView = findViewById(R.id.imageView);
        textInputEditText = findViewById(R.id.name);
        button = findViewById(R.id.btnApply);

        player = new Player();

        imageView.setOnClickListener(view -> getContent.launch("image/*"));
        button.setOnClickListener(view -> apply());
    }

    private void apply() {
        String nama = textInputEditText.getText().toString();
        Intent intent = new Intent(this, ProfilActivity.class);

        if (TextUtils.isEmpty(textInputEditText.getText())) {
            textInputEditText.setError("Nama tidak boleh kosong!");
        } else {
            player.setNama(nama);
            intent.putExtra("imageUriStr", imageUri.toString());
            intent.putExtra("user", player);
            startActivity(intent);
        }

    }
}