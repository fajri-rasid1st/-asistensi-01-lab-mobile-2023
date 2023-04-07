package com.example.assignment4;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity3 extends AppCompatActivity {

    public static final String EXTRA_FULLNAME = "extra_fullname";
    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_CAPTION = "extra_caption";
    public static final String EXTRA_PROFILE = "extra_profile";
    public static final String EXTRA_POST = "extra_post";

    private TextView tFullname, tUsername, tCapt;
    ImageView profile;
    ImageView post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tFullname = findViewById(R.id.fullname);
        tUsername = findViewById(R.id.username);
        tCapt = findViewById(R.id.caption);
        profile = findViewById(R.id.profilePic);
        post = findViewById(R.id.photo);

        String nama = getIntent().getStringExtra(EXTRA_FULLNAME);
        String username = getIntent().getStringExtra(EXTRA_USERNAME);
        String capt = getIntent().getStringExtra(EXTRA_CAPTION);
        String profil = getIntent().getStringExtra(EXTRA_PROFILE);
        String postingan = getIntent().getStringExtra(EXTRA_POST);
        post.setImageURI(Uri.parse(postingan));


        tFullname.setText(nama);
        tUsername.setText(username);
        tCapt.setText(capt);
        profile.setImageURI(Uri.parse(profil));

    }
}