package com.example.simplyfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ImageView buttonAdd, button_person, button_home;

    private ArrayList<MyItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.btn_add);
        button_person = findViewById(R.id.btn_person);
        button_home = findViewById(R.id.btn_home);

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        AddFragment addFragment = new AddFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        Fragment fragment1 = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        Fragment fragment2 = fragmentManager.findFragmentByTag(AddFragment.class.getSimpleName());
        Fragment fragment3 = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());

        if (!(fragment1 instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout,homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }

        button_home.setOnClickListener(v -> {
        if (!(fragment1 instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }
        });

        buttonAdd.setOnClickListener(v -> {
            if (!(fragment2 instanceof AddFragment)) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,addFragment,
                                AddFragment.class.getSimpleName())
                        .commit();
            }

        });

        button_person.setOnClickListener(v -> {
            if (!(fragment3 instanceof ProfileFragment)) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,profileFragment,
                                ProfileFragment.class.getSimpleName())
                        .commit();
            }
        });

    }
}