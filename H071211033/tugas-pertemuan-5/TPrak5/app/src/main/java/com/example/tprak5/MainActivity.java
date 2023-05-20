package com.example.tprak5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment =
                fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        //mengganti main layout dengan home fragment, jika home fragment bukan default
        if (!(fragment instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.cl, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }
    }
}