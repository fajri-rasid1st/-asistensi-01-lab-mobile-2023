package com.example.tugas_praktikum_6.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugas_praktikum_6.R;
import com.example.tugas_praktikum_6.data.DataSource;

public class HomeFragment extends Fragment {

    RecyclerView rvPost;
    public static DataSource dataSource = new DataSource();
    ItemAdapter adapter = new ItemAdapter(dataSource.getUsers());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rvPost = view.findViewById(R.id.rv_post);
        rvPost.setHasFixedSize(true);
        rvPost.setLayoutManager(new LinearLayoutManager(getActivity()));
//            adapter = new ItemAdapter(getActivity(), listdata);
        rvPost.setAdapter(adapter);
    }
}