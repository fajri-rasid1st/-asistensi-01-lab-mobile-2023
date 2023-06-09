package com.example.tugaspraktikum6;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tugaspraktikum6.databinding.ActivityMainBinding;

import java.io.InputStream;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding Binding;

    private FragmentManager fragmentManager;
    public static LinkedList<Post> posts = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        setDataDummy();

        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }

        Binding.homebutton.setOnClickListener(v -> switchFragment(new HomeFragment()));
        Binding.searchbutton.setOnClickListener(v -> switchFragment(new SearchFragment()));
        Binding.addbutton.setOnClickListener(v -> switchFragment(new PostFragment()));
        Binding.personbutton.setOnClickListener(v -> switchFragment(new ProfileFragment()));
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .replace(R.id.frame_container, fragment,
                        HomeFragment.class.getSimpleName())
                .commit();
    }

    private void setDataDummy() {
        Uri uri1 = Uri.parse("android.resource://com.example.tugaspraktikum6/drawable/dummy1");
        Uri uri2 = Uri.parse("android.resource://com.example.tugaspraktikum6/drawable/dummy2");
        Uri uri3 = Uri.parse("android.resource://com.example.tugaspraktikum6/drawable/dummy3");

        User user1 = new User(uri1, "Qudus", "richbriyani");
        User user2 = new User(uri2, "PoonPee", "hammus");
        User user3 = new User(uri3, "Molasses", "spicygarlic");

        posts.add(new Post(user1, "caption", uri1));
        posts.add(new Post(user2, "caption", uri2));
        posts.add(new Post(user3, "caption", uri3));
    }
}