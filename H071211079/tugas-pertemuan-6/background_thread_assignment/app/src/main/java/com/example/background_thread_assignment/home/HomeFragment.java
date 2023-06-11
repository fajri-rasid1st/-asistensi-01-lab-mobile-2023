package com.example.background_thread_assignment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.background_thread_assignment.R;
import com.example.background_thread_assignment.data.DataSource;

public class HomeFragment extends Fragment {

    RecyclerView rv_post;
    public static DataSource dataSource = new DataSource();
    PostAdapter adapter = new PostAdapter(dataSource.getUsers());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


            rv_post = view.findViewById(R.id.rv_post);
            rv_post.setHasFixedSize(true);
            rv_post.setLayoutManager(new LinearLayoutManager(getActivity()));

            rv_post.setAdapter(adapter);

    }
}