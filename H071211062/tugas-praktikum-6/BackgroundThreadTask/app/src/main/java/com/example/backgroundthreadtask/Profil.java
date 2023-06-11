package com.example.backgroundthreadtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        TextView namee = findViewById(R.id.namee);
        TextView userr = findViewById(R.id.userr);
        ImageView ppimg = findViewById(R.id.ppimg);

        Intent intent = getIntent();
        int image = intent.getIntExtra("GAMBAR", 0);
        String fullName1 = intent.getStringExtra("fullname");
        String nickName1 = intent.getStringExtra("username");

        ppimg.setImageResource(image);
        namee.setText(fullName1);
        userr.setText(nickName1);


    }
}