package com.example.background_thread_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.background_thread_assignment.data.model.Post;
import com.example.background_thread_assignment.data.model.User;
import com.example.background_thread_assignment.home.HomeFragment;
import com.example.background_thread_assignment.profile.ProfileFragment;
import com.example.background_thread_assignment.search.SearchFragment;
import com.example.background_thread_assignment.upload.AddFragment;

public class MainActivity extends AppCompatActivity {

    ImageButton btnHome, btnAdd, btnProfil, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvToolbar = findViewById(R.id.tv_toolbar);
        btnHome = findViewById(R.id.btn_home);
        btnAdd = findViewById(R.id.btn_addPost);
        btnProfil = findViewById(R.id.btn_profile);
        btnSearch = findViewById(R.id.btn_search);

        FragmentManager fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();
        SearchFragment searchFragment = new SearchFragment();
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

        btnSearch.setOnClickListener(view -> {
            tvToolbar.setText("Search");
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
        });

        btnAdd.setOnClickListener(view -> {
            tvToolbar.setText("Upload");
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, addFragment).commit();
        });

        btnProfil.setOnClickListener(view -> {
            tvToolbar.setText("Profile");

            profileFragment.setUser(new User("Dhiyaa Unnisa", "diyuubi", R.drawable.yujimin, new Post()));

            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
        });

    }
}