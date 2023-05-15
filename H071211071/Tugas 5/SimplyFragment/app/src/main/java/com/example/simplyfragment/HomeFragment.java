package com.example.simplyfragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    MyAdapter myAdapter;
    static ArrayList<MyItem> dataList = new ArrayList<>();
    ArrayList arrayList = new ArrayList<>();
    private MyItem dataSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
            if (bundle != null){
                dataSource = bundle.getParcelable("postingan");
                dataList.add(dataSource);

                Log.d("pengiriman gratis ongkir", String.valueOf(arrayList));
                System.out.println("ini postingan dalam if: " + arrayList);
        }
        for (int i = 0; i <dataList.size(); i++) {
           Log.d("datalist", String.valueOf(dataList.get(i).caption));
        }
        myAdapter = new MyAdapter(dataList);
        recyclerView.setAdapter(myAdapter);
        System.out.println(dataList);

    }

}