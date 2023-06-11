package com.example.inigaram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView profilePic;
    private TextView username,fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.profile_civ);
        username = findViewById(R.id.username_tv);
        fullname = findViewById(R.id.fullname_tv);

        Intent intent = getIntent();
        Post extra = intent.getParcelableExtra("post");

            switch (extra.getClass().getSimpleName()) {
                case "Post":
                    Post post = (Post) extra;

                    if (post.getPostPicture() != null){
                        Glide.with(this)
                                .load(post.getProfilePicture())
                                .into(profilePic);
                    }else {
                        profilePic.setImageResource(R.drawable.saya);
                    }

                    username.setText(post.getUsername());
                    fullname.setText(post.getFullname());

                    break;
                case "Profile":
                    Profile profile = (Profile) extra;
                    if (profile.getProfilePicture() != null){
                        Glide.with(this)
                                .load(profile.getProfilePicture())
                                .into(profilePic);
                    }else {
                        profilePic.setImageResource(R.drawable.saya);
                    }

                    username.setText(profile.getUsername());
                    fullname.setText(profile.getFullname());
                    break;
            }

    }
}