package com.example.fragment_assignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        TextView textView = view.findViewById(R.id.textView);

        if (bundle != null) {
            ArrayList<PostModel> posts = bundle.getParcelableArrayList("keyPost");
            RecyclerView rv_post = view.findViewById(R.id.rv_post);
            rv_post.setHasFixedSize(true);
            rv_post.setLayoutManager(new LinearLayoutManager(getActivity()));
            PostAdapter postAdapter = new PostAdapter(getContext(), posts);
            rv_post.setAdapter(postAdapter);
            textView.setVisibility(View.GONE);

        } else {
            textView.setText("Please add a post first");
        }
    }
}