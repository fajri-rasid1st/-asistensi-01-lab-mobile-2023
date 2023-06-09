package com.example.tugaspraktikum6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tugaspraktikum6.databinding.FragmentPostBinding;
import com.example.tugaspraktikum6.databinding.FragmentSearchBinding;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    ArrayList<User> users = new ArrayList<>();
    SearchAdapter myAdapter;
    ProgressBar progressBar;
    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = binding.pb;
        for (Post posts : MainActivity.posts) {
            users.add(posts.getUser());
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              progressBar.setVisibility(View.INVISIBLE);
            }
        }, 1000);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                suggestSearch(newText);
                return true;
            }

        });

    }

    private void suggestSearch(String query) {
        ArrayList<User> filterUsers = new ArrayList<>();
//        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users.get(i).getUsername());
//
//        }
        for (User user : users) {
            if (user.getNickname().toLowerCase().contains(query.toLowerCase()) || user.getUsername().toLowerCase().contains(query.toLowerCase())) {
                filterUsers.add(user);
            }
        }

        viewUser(filterUsers);
    }

    private void viewUser(ArrayList<User> filterUsers) {
        if (filterUsers.isEmpty()) {
            Toast.makeText(getActivity(), "Username not found", Toast.LENGTH_SHORT).show();
        }
        binding.rvSuggestions.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter  = new SearchAdapter(filterUsers);
        binding.rvSuggestions.setAdapter(myAdapter);
    }

}