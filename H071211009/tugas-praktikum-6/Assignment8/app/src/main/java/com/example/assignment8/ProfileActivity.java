package com.example.assignment8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProfileActivity extends AppCompatActivity {
    TextView fullname, username;
    ImageView profilpic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilpic = findViewById(R.id.foto_profil);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);

        Intent data = getIntent();
        fullname.setText(data.getStringExtra("fullname"));
        username.setText(data.getStringExtra("username"));

        Glide.with(this)
                .load(data.getStringExtra("fotoprofile"))
                .into(profilpic);

    }
}