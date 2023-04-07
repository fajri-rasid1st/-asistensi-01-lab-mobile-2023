package com.example.intentassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class PostCaption extends AppCompatActivity {

    ImageView imgCap;
    EditText caption;
    Button btnUp;
    int SELECT_IMAGE = 1;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_caption);
        btnUp = findViewById(R.id.btnUp);
        caption = findViewById(R.id.caption);
        imgCap = findViewById(R.id.imgCap);

        btnUp.setOnClickListener(view -> {
            String cap = caption.getText().toString();
            Uri uri1 = getIntent().getParcelableExtra("imgProf");
            if (cap.isEmpty() && imgCap == null) {
                Toast.makeText(this, "Lengkapi Dahulu", Toast.LENGTH_SHORT).show();
                caption.setError("Harus DiIsi");
            }else if(uri == null) {
                Toast.makeText(this, "Lengkapi Dahulu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(PostCaption.this, Result.class);
                String uName= getIntent().getStringExtra("username");
                String fName= getIntent().getStringExtra("fullname");
                intent.putExtra("img", uri);
                intent.putExtra("fullname", fName);
                intent.putExtra("username", uName);
                intent.putExtra("caption", cap);
                intent.putExtra("imgProf", uri1);
                startActivity(intent);
            }
        });


        imgCap.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            uri = data.getData();
            data.getData();
            imgCap.setImageURI(uri);
        }
    }
}