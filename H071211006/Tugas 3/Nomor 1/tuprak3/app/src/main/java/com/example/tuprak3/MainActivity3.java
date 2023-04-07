package com.example.tuprak3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private ImageView imgProfile, imgPost;
    private TextView user, name, capt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        imgProfile = findViewById(R.id.profile_img);
        imgPost = findViewById(R.id.img_post);
        user = findViewById(R.id.username);
        name = findViewById(R.id.fullname);
        capt = findViewById(R.id.caption);

        String userName = getIntent().getStringExtra("keyusername");
        String fullName = getIntent().getStringExtra("keyfullname");
        String readCaption = getIntent().getStringExtra("keycaption");
        Uri profile = getIntent().getParcelableExtra("keyprofile");
        Uri post = getIntent().getParcelableExtra("keypost");

        imgProfile.setImageURI(profile);
        imgPost.setImageURI(post);
        user.setText(userName);
        name.setText(fullName);
        capt.setText(readCaption);
    }
}