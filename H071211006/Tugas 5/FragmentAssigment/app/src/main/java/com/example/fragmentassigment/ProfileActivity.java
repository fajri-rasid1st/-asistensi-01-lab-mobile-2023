package com.example.fragmentassigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

//    private ImageView ivProfile;
//    private TextView tvUsername, tvFullName;
//    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
        fragmentManager.beginTransaction().add(R.id.frame_profile_container, new ProfileFragment(), ProfileFragment.class.getSimpleName()).commit();

//        ivProfile = findViewById(R.id.iv_profile);
//        tvUsername = findViewById(R.id.tv_user);
//        tvFullName = findViewById(R.id.tv_name);

    }
}