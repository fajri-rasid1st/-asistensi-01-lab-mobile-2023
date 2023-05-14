package com.example.praktikumrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RoomChatActivity extends AppCompatActivity {

    private TextView tvName;
    private ImageView ivPhoto, back;
    private RecyclerView rvRoomChat;
    private LinearLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_chat);

        tvName = findViewById(R.id.tv_name);
        ivPhoto = findViewById(R.id.iv_photo);
        header = findViewById(R.id.header);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        String name = intent.getStringExtra("varName");
        String photo = intent.getStringExtra("varPhoto");
        String status = intent.getStringExtra("varStatus");
        String dateOfStatus = intent.getStringExtra("varDateOfStatus");
        String phone = intent.getStringExtra("varPhone");
        String lastChat = intent.getStringExtra("varLastChat");

        tvName.setText(name);

        Glide.with(RoomChatActivity.this)
                .load(photo)
                .into(ivPhoto);

        rvRoomChat = findViewById(R.id.rv_roomChat);
        rvRoomChat.setHasFixedSize(true);
        rvRoomChat.setLayoutManager(new LinearLayoutManager(this));

        setUpAdapter(lastChat);
        header.setOnClickListener(view -> {
            Intent intent1 = new Intent(RoomChatActivity.this, ContactActivity.class);
            intent1.putExtra("varName", name);
            intent1.putExtra("varStatus", status);
            intent1.putExtra("varDateOfStatus", dateOfStatus);
            intent1.putExtra("varPhoto", photo);
            intent1.putExtra("varPhone", phone);

            startActivity(intent1);
        });

        back.setOnClickListener(view -> {
            finish();
        });
    }

    private void setUpAdapter(String lastChat) {
        ArrayList<Chat> chats = DataSource.getListChat();

        Chat selectedChat = chats.stream().filter(chat -> chat.getLastChat().equals(lastChat)).collect(Collectors.toList()).get(0);

        chats.remove(selectedChat);

        chats.add(selectedChat);

        RoomChatAdapter roomChatAdapter = new RoomChatAdapter(chats);
        rvRoomChat.setAdapter(roomChatAdapter);
    }

//    public void move(View view) {
//        Intent intent = new Intent(RoomChatActivity.this, ChatAdapter.class);
//        startActivity(intent);
//    }
}