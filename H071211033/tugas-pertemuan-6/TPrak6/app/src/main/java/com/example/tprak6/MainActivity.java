package com.example.tprak6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tprak6.data.model.Post;
import com.example.tprak6.data.model.User;
import com.example.tprak6.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private User user;

    public User getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User("alfdhail", "Muhammad Al Fudhail", R.drawable.profile, new Post());

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment =
                fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.cl, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }
    }
}