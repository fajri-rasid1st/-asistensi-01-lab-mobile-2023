package com.example.assignment7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    AddFragment addFragment = new AddFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    ImageButton ibHome, ibAdd, ibProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibHome = findViewById(R.id.btnHome);
        ibAdd = findViewById(R.id.btnAdd);
        ibProfile = findViewById(R.id.btnProfile);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        if(!(fragment instanceof HomeFragment)){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        ibHome.setOnClickListener(v -> {
            Bundle bundle = getIntent().getExtras();
            homeFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
        });
        ibAdd.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, addFragment).commit();
        });
        ibProfile.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, profileFragment).commit();
        });

    }
}