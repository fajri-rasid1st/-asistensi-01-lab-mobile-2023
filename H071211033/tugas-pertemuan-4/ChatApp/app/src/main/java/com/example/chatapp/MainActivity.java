package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.chatapp.Adapters.ConvoAdapter;
import com.example.chatapp.DataSource.ChatsDataSource;
import com.example.chatapp.DataSource.UserDataSource;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvConvo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvConvo = findViewById(R.id.rvConvo);
        rvConvo.setHasFixedSize(true);
        rvConvo.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.divider);
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable);
        }
        rvConvo.addItemDecoration(dividerItemDecoration);


        setUpAdapter();
    }

    private void setUpAdapter() {
        rvConvo.setAdapter(new ConvoAdapter(UserDataSource.getUserList(), ChatsDataSource.getChatsList()));
    }
}