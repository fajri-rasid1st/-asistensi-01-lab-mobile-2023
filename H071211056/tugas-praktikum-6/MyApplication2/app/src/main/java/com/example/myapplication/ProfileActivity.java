package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.ProfileFragment;
import com.example.myapplication.models.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user" ;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

         user =  getIntent().getParcelableExtra(EXTRA_USER);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

           handler.post(() -> {
               ProgressBar progressBar = findViewById(R.id.progress_bar);
               progressBar.setVisibility(View.GONE);

               FragmentManager fragmentManager = getSupportFragmentManager();

               Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());

               if (!(fragment  instanceof HomeFragment)){
                   ProfileFragment profileFragment = new ProfileFragment();
                   profileFragment.setUser(user);
                   fragmentManager.beginTransaction()
                           .replace(R.id.profile_container, profileFragment, ProfileFragment.class.getSimpleName())
                           .commit();

               }
           });
        });

    }

}
