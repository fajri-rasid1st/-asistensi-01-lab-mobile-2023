package com.example.shchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    private ImageView ivBack;
    private CircleImageView ivFoto;
    private TextView tvNama;
    private RecyclerView rvChat;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ivBack = findViewById(R.id.ivBack);
        ivFoto = findViewById(R.id.ivFoto);
        tvNama = findViewById(R.id.tvNama);
        rvChat = findViewById(R.id.rvChat);
        user = getIntent().getParcelableExtra("Profile");
        ivFoto.setImageResource(user.getProfile());
        tvNama.setText(user.getName());

        setUpAdapter();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setUpAdapter() {
        // create LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // set layout manager
        rvChat.setLayoutManager(layoutManager);
        // set fixed size
        rvChat.setHasFixedSize(true);
        // create and set adapter
        ChatAdapter chatAdapter = new ChatAdapter(DataSourceChat.getListChat());
        rvChat.setAdapter(chatAdapter);
    }
}