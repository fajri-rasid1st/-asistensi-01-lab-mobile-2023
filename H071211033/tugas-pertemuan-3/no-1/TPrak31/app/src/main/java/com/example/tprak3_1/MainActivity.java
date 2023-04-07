package com.example.tprak3_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Uri imageUri;
    private ImageView imageView;
    private TextInputEditText textInputEditText1, textInputEditText2;


    private final ActivityResultLauncher<String> getContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if (uri != null) {
                        imageUri = uri;
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.setImageURI(uri);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textInputEditText1 = findViewById(R.id.name);
        textInputEditText2 = findViewById(R.id.username);
        Button button = findViewById(R.id.btnSubmit);

        imageView.setOnClickListener(view -> getContent.launch("image/*"));
        button.setOnClickListener(view -> submit());

    }

    private void submit() {
        String name = textInputEditText1.getText().toString().trim();
        String username = textInputEditText2.getText().toString().trim();
        Boolean isEditText1Empty = TextUtils.isEmpty(textInputEditText1.getText());
        Boolean isEditText2Empty = TextUtils.isEmpty(textInputEditText2.getText());

        if (imageUri == null) {
            Toast.makeText(this, "Please set a profile picture", Toast.LENGTH_SHORT).show();
        } else if (isEditText1Empty || isEditText2Empty) {
            if (isEditText1Empty) {
                textInputEditText1.setError("Field cannot be empty");
            }
            if (isEditText2Empty) {
                textInputEditText2.setError("Field cannot be empty");
            }
        } else {
            Intent intent = new Intent(this, PostActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("username", username);
            intent.putExtra("imageUri", imageUri.toString());
            startActivity(intent);
        }
    }
}