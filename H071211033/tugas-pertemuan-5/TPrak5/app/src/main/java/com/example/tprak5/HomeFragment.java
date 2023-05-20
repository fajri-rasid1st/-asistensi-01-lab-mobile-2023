package com.example.tprak5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    static PostAdapter postAdapter = new PostAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvPost = view.findViewById(R.id.rvPost);
        ImageButton btnUpload = view.findViewById(R.id.btnUpload);
        ImageButton btnProfile = view.findViewById(R.id.btnProfile);

        btnUpload.setOnClickListener(v -> {

            UploadFragment uploadFragment = new UploadFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, uploadFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnProfile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        //memanggil adapter
        rvPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPost.setAdapter(postAdapter);

        //textview di home akan hlang jika ada item dari recyclerview
        if (postAdapter.getItemCount() > 0) {

            TextView textView = view.findViewById(R.id.textView4);
            textView.setVisibility(View.GONE);
        }
    }
}