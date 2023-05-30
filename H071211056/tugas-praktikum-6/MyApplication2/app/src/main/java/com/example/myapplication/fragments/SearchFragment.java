package com.example.myapplication.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.myapplication.SearchAdapter;
import com.example.myapplication.DataSource;
import com.example.myapplication.R;
import com.example.myapplication.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView2;
    private SearchAdapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private ArrayList<User> data = HomeFragment.dataSource.getUsers();
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);

        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(recyclerViewLayoutManager);

        recyclerAdapter = new SearchAdapter( data);
        recyclerView2.setAdapter(recyclerAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                progressBar.setVisibility(View.VISIBLE);
                recyclerView2.setVisibility(View.GONE);

                executor.execute(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    handler.post(() -> {
                        List<User> itemFilter = filterItems(newText.toLowerCase());
                        recyclerAdapter.setFilter((ArrayList<User>) itemFilter);
                        progressBar.setVisibility(View.GONE);
                        recyclerView2.setVisibility(View.VISIBLE);

                    });
                });

                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private List<User> filterItems(String newText) {
        ArrayList<User> itemFilter = new ArrayList<>();
        for (User model : data) {
            String nama = model.getFullName().toLowerCase();
            if (nama.contains(newText) ) {
                itemFilter.add(model);
            }
        }
        return itemFilter;
    }
}