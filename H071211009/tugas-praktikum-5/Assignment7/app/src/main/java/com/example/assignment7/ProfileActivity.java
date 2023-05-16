package com.example.assignment7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(ProfileFragment.class.getSimpleName());
        if(!(fragment instanceof ProfileFragment)){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activityProfile, new ProfileFragment(), ProfileFragment.class.getSimpleName())
                    .commit();
        }
    }
}