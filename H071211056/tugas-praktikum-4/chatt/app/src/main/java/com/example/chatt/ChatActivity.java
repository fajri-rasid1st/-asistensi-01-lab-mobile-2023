package com.example.chatt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ImageView img = findViewById(R.id.img1);
        TextView name = findViewById(R.id.name1);
        Button chat = findViewById(R.id.chat1);

        Intent intent = getIntent();

        int logo = intent.getIntExtra("GAMBAR_DEFAULT",0);
        String namaChat = intent.getStringExtra("TEKS_DEFAULT1");
        String chati = intent.getStringExtra("TEKS_DEFAULT2");

        img.setImageResource(logo);
        name.setText(namaChat);
        chat.setText(chati);

    }
}