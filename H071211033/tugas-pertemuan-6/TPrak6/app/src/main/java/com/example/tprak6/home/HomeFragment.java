package com.example.tprak6.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprak6.MainActivity;
import com.example.tprak6.data.model.User;
import com.example.tprak6.profile.ProfileFragment;
import com.example.tprak6.R;
import com.example.tprak6.search.SearchFragment;
import com.example.tprak6.upload.UploadFragment;
import com.example.tprak6.data.DataSource;

public class HomeFragment extends Fragment {

    MainActivity mainActivity;
    public static DataSource dataSource = new DataSource();
    public static PostAdapter postAdapter = new PostAdapter(dataSource.getUsers());

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
        ImageButton btnSearch = view.findViewById(R.id.btnSearch);
        mainActivity = (MainActivity) getActivity();

        rvPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPost.setAdapter(postAdapter);

        btnUpload.setOnClickListener(v -> {

            UploadFragment uploadFragment = new UploadFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, uploadFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnProfile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();

            profileFragment.setUser(mainActivity.getUser());

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnSearch.setOnClickListener(v -> {
            SearchFragment searchFragment = new SearchFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, searchFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}