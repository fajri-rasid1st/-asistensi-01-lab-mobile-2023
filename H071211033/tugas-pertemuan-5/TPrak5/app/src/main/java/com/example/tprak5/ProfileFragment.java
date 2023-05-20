package com.example.tprak5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btnUpload = view.findViewById(R.id.btnUpload);
        ImageButton btnHome = view.findViewById(R.id.btnHome);

        btnUpload.setOnClickListener(v -> {
            UploadFragment uploadFragment = new UploadFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, uploadFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnHome.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, homeFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}