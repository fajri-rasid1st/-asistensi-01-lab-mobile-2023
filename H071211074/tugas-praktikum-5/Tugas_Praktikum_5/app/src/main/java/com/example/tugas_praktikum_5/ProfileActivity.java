package com.example.tugas_praktikum_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ProfilFragment.class.getSimpleName());
        if (!(fragment instanceof ProfilFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.profile_container, new ProfilFragment(), ProfilFragment.class.getSimpleName())
                    .commit();
        }

    }
}