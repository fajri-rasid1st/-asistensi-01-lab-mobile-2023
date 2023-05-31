package com.example.tprak6.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.tprak6.MainActivity;
import com.example.tprak6.R;
import com.example.tprak6.data.model.User;
import com.example.tprak6.home.HomeFragment;
import com.example.tprak6.profile.ProfileFragment;
import com.example.tprak6.upload.UploadFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    SearchAdapter searchAdapter;
    MainActivity mainActivity;

    List<User> filteredUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        filteredUsers = new ArrayList<>();
        mainActivity = (MainActivity) getActivity();

        Handler handler = new Handler();
        ImageButton btnUpload = view.findViewById(R.id.btnUpload);
        ImageButton btnHome = view.findViewById(R.id.btnHome);
        ImageButton btnProfile = view.findViewById(R.id.btnProfile);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        SearchView searchView = view.findViewById(R.id.searchView);
        RecyclerView rvSearchResult = view.findViewById(R.id.rvSearchResult);

        searchView.clearFocus();
        rvSearchResult.setVisibility(View.INVISIBLE);
        searchAdapter = new SearchAdapter(filteredUsers);
        rvSearchResult.setAdapter(searchAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterItem(newText);
                return true;
            }
        });

        handler.postDelayed(() -> {

            progressBar.setVisibility(View.INVISIBLE);
            rvSearchResult.setVisibility(View.VISIBLE);
        }, 1200);

        rvSearchResult.setHasFixedSize(true);
        rvSearchResult.setLayoutManager(new LinearLayoutManager(view.getContext()));

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

        btnHome.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, homeFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

    }

    private void filterItem(String textSearch) {

        filteredUsers.clear();

        if (textSearch.isEmpty()) {
            searchAdapter.notifyDataSetChanged();
            return;
        }

        ArrayList<User> users = HomeFragment.dataSource.getUsers();

        User user = users.get(0);

        for (int i = 0; i < users.size(); i++) {

            User user1 = users.get(i);

            if (i > 0) {

                if (user.getUsername().equals(user1.getUsername()) ||
                        user.getName().equals(user1.getName())) {

                    continue;
                }
            }

            if (user1.getName().toLowerCase().contains(textSearch.toLowerCase()) ||
                    user1.getUsername().toLowerCase().contains(textSearch.toLowerCase())) {

                filteredUsers.add(user1);
            }

            user = user1;
        }

        searchAdapter.notifyDataSetChanged();
    }

}