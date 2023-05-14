package com.example.tugas_praktikum_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private AddPostFragment addPostFragment;
    private ProfilFragment profilFragment;
    ImageButton btnHome, btnAddPost, btnProfil;
    TextView tvToolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = findViewById(R.id.btn_home);
        btnAddPost = findViewById(R.id.btn_add);
        btnProfil = findViewById(R.id.btn_profil);
        tvToolbar = findViewById(R.id.tv_toolbar);

        homeFragment = new HomeFragment();
        addPostFragment = new AddPostFragment();
        profilFragment = new ProfilFragment();

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

        btnProfil.setOnClickListener(v -> navigateFragment(profilFragment, "Profile"));
    }

    public void navigateFragment(Fragment fragment, String text) {
        tvToolbar.setText(text);
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }
}