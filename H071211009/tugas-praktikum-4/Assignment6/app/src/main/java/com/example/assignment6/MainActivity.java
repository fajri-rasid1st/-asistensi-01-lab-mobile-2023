package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ModelContact> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup rv
        RecyclerView rvContacts = findViewById(R.id.rvContact);
        rvContacts.setHasFixedSize(true);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        // set adapter to rv
        AdapterMainPage adapter = new AdapterMainPage(DataSource.contacts);
        adapter.setClickListener(new AdapterMainPage.ClickListener() {

            @Override
            public void onItemClicked(ModelContact thisContact) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                //ini awal untuk extra chat
                intent.putExtra(ChatActivity.EXTRA_CHAT, thisContact);
                startActivity(intent);
            }
        });
        rvContacts.setAdapter(adapter);
    }
}