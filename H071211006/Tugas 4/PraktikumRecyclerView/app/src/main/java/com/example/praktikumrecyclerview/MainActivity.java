package com.example.praktikumrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChats = findViewById(R.id.rv_chats);
        rvChats.setHasFixedSize(true);
        rvChats.setLayoutManager(new LinearLayoutManager(this));
        rvChats.addItemDecoration(
                new DividerItemDecoration(rvChats.getContext(), DividerItemDecoration.VERTICAL)
        );

        setUpAdapter();
    }

    private void setUpAdapter() {
        ChatAdapter chatAdapter = new ChatAdapter(DataSource.getListChat());
        rvChats.setAdapter(chatAdapter);
    }
}