package com.example.tugas3_quizapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class UserActivity extends AppCompatActivity {

    ImageView profilePict;
    EditText userName;
    Button button;
    Uri uri;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent intent = result.getData();

            if (intent != null) {
                uri = intent.getData();
                profilePict.setImageURI(uri);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        profilePict = findViewById(R.id.profilePict);
        userName = findViewById(R.id.username);
        button = findViewById(R.id.submit);

        profilePict.setOnClickListener(view -> click());

        button.setOnClickListener(view -> {

            if (uri != null) {
                if (!isFieldEmpty(userName)) {
                    goToQuiz();
                }
            } else {
                Toast.makeText(UserActivity.this, "Harus Masukkan Foto", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void goToQuiz() {
        Intent tes = new Intent(UserActivity.this, QuizActivity.class);
        tes.putExtra("profilePict", uri.toString());
        tes.putExtra("username", userName.getText().toString());
        startActivity(tes);
    }

    public void click() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launcher.launch(Intent.createChooser(pickPhoto, "Choose a photo"));
    }

    public boolean isFieldEmpty(EditText... editTexts) {
        boolean isEmpty = false;

        for (EditText editText : editTexts) {
            String text = editText.getText().toString().trim();

            if (text.isEmpty()) {
                editText.setError("Field Tidak Boleh Kosong");
                isEmpty = true;
            }

        }

        return isEmpty;
    }
}