package com.example.tugas_praktikum_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tugas_praktikum_6.data.model.Post;
import com.example.tugas_praktikum_6.data.model.User;
import com.example.tugas_praktikum_6.home.HomeFragment;
import com.example.tugas_praktikum_6.profile.ProfileFragment;
import com.example.tugas_praktikum_6.search.SearchFragment;
import com.example.tugas_praktikum_6.upload.AddPostFragment;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private AddPostFragment addPostFragment;
    private ProfileFragment profileFragment;
    private SearchFragment searchFragment;
    ImageButton btnHome, btnAddPost, btnProfile, btnSearch;
    TextView tvToolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = findViewById(R.id.btn_home);
        btnAddPost = findViewById(R.id.btn_add);
        btnProfile = findViewById(R.id.btn_profil);
        btnSearch = findViewById(R.id.btn_search);
        tvToolbar = findViewById(R.id.tv_toolbar);

        homeFragment = new HomeFragment();
        addPostFragment = new AddPostFragment();
        profileFragment = new ProfileFragment();
        searchFragment = new SearchFragment();

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        btnHome.setOnClickListener(v -> {
            Bundle bundle = getIntent().getExtras();
            homeFragment.setArguments(bundle);
            navigateFragment(homeFragment, "Inigaram");
        });

        btnAddPost.setOnClickListener(v -> navigateFragment(addPostFragment, "Upload"));

        btnProfile.setOnClickListener(v -> {
            profileFragment.setUser(new User("dede", "Ananda", R.drawable.hanni, new Post()));

            navigateFragment(profileFragment, "Profile");
        });

        btnSearch.setOnClickListener(v -> navigateFragment(searchFragment, "Search"));
    }

    public void navigateFragment(Fragment fragment, String text) {
        tvToolbar.setText(text);
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }
}