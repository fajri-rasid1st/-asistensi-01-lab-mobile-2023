package com.example.fragmentassigment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private TextView tvCaption;
    private ImageView ivProfile;
    private RecyclerView recyclerView;
    private AdapterPost adapter;
    private ArrayList<DataPost> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        TextView textView = view.findViewById(R.id.tv_addPhoto);

        if (bundle != null) {
            ArrayList<DataPost> dataList = bundle.getParcelableArrayList("key_post");
            recyclerView = view.findViewById(R.id.rv_post);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new AdapterPost(getContext(), dataList);
            recyclerView.setAdapter(adapter);
            textView.setVisibility(View.GONE);


        } else {

            textView.setText("Please add a post first!");
        }
    }
}

