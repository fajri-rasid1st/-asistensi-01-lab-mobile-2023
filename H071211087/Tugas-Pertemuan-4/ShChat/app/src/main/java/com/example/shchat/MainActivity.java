package com.example.shchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        recyclerView = findViewById(R.id.recyclerView);
        //--
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setUpAdapter();
    }
    private void setUpAdapter() {
        //-
        MainAdapter foodAdapter = new MainAdapter(UserDataSource.getListUser(),DataSourceChat.getListChat());
        //--
        recyclerView.setAdapter(foodAdapter);




    }
}