package com.example.tprak3_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {

    private Uri imageUri;
    private ImageView imageView;
    private EditText editText;

    private final ActivityResultLauncher<String> getContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
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
        setContentView(R.layout.activity_post);

        imageView = findViewById(R.id.ivPost);
        editText = findViewById(R.id.caption);
        Button button = findViewById(R.id.btnUp);

        imageView.setOnClickListener(view -> getContent.launch("image/*"));
        button.setOnClickListener(view -> up());
    }

    private void up() {
        String caption = editText.getText().toString();

        if (imageUri == null) {
            Toast.makeText(this, "Please set a post image", Toast.LENGTH_SHORT).show();
        } else {
            String name = getIntent().getStringExtra("name");
            String username = getIntent().getStringExtra("username");
            String imageUri1Str = getIntent().getStringExtra("imageUri");

            Intent intent1 = new Intent(this, ResultActivity.class);
            intent1.putExtra("imageUri2", imageUri.toString());
            intent1.putExtra("caption", caption);
            intent1.putExtra("name", name);
            intent1.putExtra("username", username);
            intent1.putExtra("imageUri1Str", imageUri1Str);
            startActivity(intent1);
        }
    }

}