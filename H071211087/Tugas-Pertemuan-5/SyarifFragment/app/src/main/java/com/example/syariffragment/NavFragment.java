package com.example.syariffragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class NavFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nav, container, false);

        LinearLayout bottomNavigation = view.findViewById(R.id.bottom_navigation);
        ImageView homeButton = view.findViewById(R.id.home_button);
        ImageView postButton = view.findViewById(R.id.add_button);
        ImageView profileButton = view.findViewById(R.id.profile_button);




        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to HomeFragment
                Fragment homeFragment = new HomeFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, homeFragment)
                        .commit();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to PostFragment
                Fragment postFragment = new AddFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, postFragment)
                        .commit();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ProfFragment
                Fragment profFragment = new ProfileFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, profFragment)
                        .commit();
            }
        });
        return view;
    }
}