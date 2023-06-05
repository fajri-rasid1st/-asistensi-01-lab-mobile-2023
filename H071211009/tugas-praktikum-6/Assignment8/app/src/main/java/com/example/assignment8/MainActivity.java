package com.example.assignment8;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    AddFragment addFragment = new AddFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    ImageButton ibHome, ibSearch, ibAdd, ibProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        addFragment = new AddFragment();
        profileFragment = new ProfileFragment();

        ibHome = findViewById(R.id.btnHome);
        ibSearch = findViewById(R.id.btnSearch);
        ibAdd = findViewById(R.id.btnAdd);
        ibProfile = findViewById(R.id.btnProfile);

        // Memeriksa apakah fragment dengan tag HomeFragment sudah ada
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        if(!(fragment instanceof HomeFragment)){
            // Jika tidak ada, tambahkan HomeFragment ke dalam container
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        // Mengatur listener untuk ImageButton ibHome
        ibHome.setOnClickListener(v -> {
            Bundle bundle = getIntent().getExtras();
            homeFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
        });

        // Mengatur listener untuk ImageButton ibSearch
        ibSearch.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, searchFragment).commit();
        });

        // Mengatur listener untuk ImageButton ibAdd
        ibAdd.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, addFragment).commit();
        });

        // Mengatur listener untuk ImageButton ibProfile
        ibProfile.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, profileFragment).commit();
        });

    }
}