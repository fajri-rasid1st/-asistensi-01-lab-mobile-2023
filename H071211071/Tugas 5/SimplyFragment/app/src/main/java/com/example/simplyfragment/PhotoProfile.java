package com.example.simplyfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class PhotoProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_profile);

        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        ProfileFragment profileFragment = new ProfileFragment();
        fragmentManager
                .beginTransaction()
                .replace(R.id.malade,profileFragment,
                        ProfileFragment.class.getSimpleName())
                .commit();
    }
}