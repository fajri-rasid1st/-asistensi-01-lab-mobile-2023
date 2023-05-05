package com.example.rv_assignment;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass>userList;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initRecylerView();


    }

    private void initData() {

        userList = new ArrayList<>();

        userList.add(new ModelClass(R.drawable.hendery, "Hendery", "Hendery", "081234678893", "Aktif", "10 Mei 2021", "Kenapa bisa?", "10:30pm"));
        userList.add(new ModelClass(R.drawable.leetaeyong, "Taeyong", "Taeyong", "085633489976", "Online", "8 Februari 2020", "Astaga ndk bisa begitu", "07:30pm"));
        userList.add(new ModelClass(R.drawable.jeno, "Jeno", "Jeno", "081256343211", "hi cantik", "19 Februari 2022", "Orang gila", "10:30am"));
        userList.add(new ModelClass(R.drawable.kai, "Kai", "Kai", "0899780674533", "hi", "18 November 2020", "hehehhe", "08:25pm"));
        userList.add(new ModelClass(R.drawable.mark, "Mark", "Mark", "083478572218", "nack gwl", "20 Agustus 2021", "Jangan chat aku lagi", "01:00pm"));
        userList.add(new ModelClass(R.drawable.chanyeol, "Chanyeol", "Chanyeol", "081789034566", "tidak aktif", "1 Januari 2023", "oh cukup tw", "05:30pm"));
        userList.add(new ModelClass(R.drawable.doyoung, "Doyoung", "Doyoung", "081276555544", "off", "26 Maret 2023", "masa???", "06:30am"));
        userList.add(new ModelClass(R.drawable.jisung, "Jisung", "Jisung", "081222233344", "ndk mood", "11 Mei 2021", "oma omaga", "05:15am"));
        userList.add(new ModelClass(R.drawable.sungchan, "Sungchan", "Sungchan", "085621348890", "malas menanggapi", "16 Juni 2022", "kmu mw gk kepalanya ketancep bening?", "08:00am"));
        userList.add(new ModelClass(R.drawable.sehun, "Sehun", "Sehun", "085123908657", "cwk kul jgn chat", "5 Januari 2022", "p", "03:00am"));
        userList.add(new ModelClass(R.drawable.dyo, "D.O.", "D.O.", "081278936523", "oke", "30 April 2023", "kita cuma teman ndk lebih", "10:00pm"));

    }

    private void initRecylerView() {

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}