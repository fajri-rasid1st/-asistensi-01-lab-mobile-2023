package com.example.background_thread_assignment.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.background_thread_assignment.R;
import com.example.background_thread_assignment.data.model.User;
import com.example.background_thread_assignment.home.HomeFragment;
import com.example.background_thread_assignment.profile.ProfileFragment;
import com.google.android.material.search.SearchView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class SearchFragment extends Fragment {

    TextInputEditText tvSearch;
    RecyclerView rvSearch;
    ProgressBar pbSearch;
    SearchAdapter searchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvSearch = view.findViewById(R.id.tv_search);
        rvSearch = view.findViewById(R.id.rv_search);
        pbSearch = view.findViewById(R.id.pb_search);
        searchAdapter = new SearchAdapter();
        rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSearch.setAdapter(searchAdapter);

        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pbSearch.setVisibility(View.VISIBLE);
                rvSearch.setVisibility(View.GONE);
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                executor.execute(()-> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    handler.post(()-> {
                        if (TextUtils.isEmpty(s)) {
                            searchAdapter.setUsers(new ArrayList<>());
                        } else {
                            searchAdapter.setUsers(searchUser(s.toString()));

                        }
                        searchAdapter.notifyDataSetChanged();
                        pbSearch.setVisibility(View.GONE);
                        rvSearch.setVisibility(View.VISIBLE);
                    });
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private ArrayList<User> searchUser(String text){
        ArrayList<User> users = HomeFragment.dataSource.getUsers();
        ArrayList<User> filteredUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            String textLower = text.toLowerCase();
            String fullnameLower = user.getFullname().toLowerCase();
            String usernameLower = user.getUsername().toLowerCase();

            if (fullnameLower.startsWith(textLower)|| usernameLower.startsWith(textLower)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
}