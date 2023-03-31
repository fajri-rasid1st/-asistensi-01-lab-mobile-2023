package com.example.intent_assignment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.intent_assignment.databinding.ActivityMain2Binding;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    ActivityResultLauncher <Intent> imageSelectLauncher;
    private ActivityMain2Binding binding;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageSelectLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK || result.getData() != null) {
                Intent intent = result.getData();
                Uri uri = intent != null ? intent.getData() : null;

                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    binding.imagePost.setImageBitmap(image);
                } catch (IOException ioException) {
                    throw new RuntimeException(ioException);
                }
            }
        });
        binding.imagePost.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageSelectLauncher.launch(intent);
        });

        binding.buttonUpload.setOnClickListener(view -> {
            Intent upload = new Intent(this, MainActivity3.class);
            Post.deskripsi = binding.editText1.getText().toString();
            Post.photo = image;

            if (Post.deskripsi.isEmpty() || Post.photo == null) {
                if (Post.deskripsi.isEmpty()) {
                    binding.editText1.setError("Silahkan di isi!");
                }
                if (Post.photo == null) {
                    Toast.makeText(this, "Silahkan masukkan foto!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            startActivity(upload);
        });
    }
}