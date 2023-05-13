package com.example.fragment_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton btnHome, btnAdd, btnProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvToolbar = findViewById(R.id.tv_toolbar);
        btnHome = findViewById(R.id.btn_home);
        btnAdd = findViewById(R.id.btn_addPost);
        btnProfil = findViewById(R.id.btn_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();
        AddFragment addFragment = new AddFragment();

        ProfileFragment profileFragment = new ProfileFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.flFragment, homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        btnHome.setOnClickListener(view -> {
            tvToolbar.setText("Inigaram");
            Bundle bundle = getIntent().getExtras();
            homeFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
        });

        btnAdd.setOnClickListener(view -> {
            tvToolbar.setText("Upload");
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, addFragment).commit();
        });

        btnProfil.setOnClickListener(view -> {
            tvToolbar.setText("Profile");
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
        });

    }
}