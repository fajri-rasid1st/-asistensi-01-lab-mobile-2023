package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.fragments.AddFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.ProfileFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)){
            navigateFragment(new HomeFragment());
        }

        navigationListener();
    }

    private void navigationListener() {
        ImageView home =  findViewById(R.id.tabHome);
        ImageView add = findViewById(R.id.tabAdd);
        ImageView profile = findViewById(R.id.tabProfile);


        home.setOnClickListener(v -> navigateFragment(new HomeFragment()));
        add.setOnClickListener(v -> navigateFragment(new AddFragment()));
        profile.setOnClickListener(v -> navigateFragment(new ProfileFragment()));
    }

    public void navigateFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}