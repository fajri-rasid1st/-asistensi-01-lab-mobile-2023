package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ChatActivity extends AppCompatActivity {

    //untuk intent extra chat
    public static final String EXTRA_CHAT = "extra_chat";

    private ImageButton btnBack;
    private TextView tvContactName;
    private ImageView profilePict;
    private RelativeLayout forContactDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //for get intent
        ModelContact contactMainPage = getIntent().getParcelableExtra(EXTRA_CHAT);

        //setup rv
        RecyclerView rvChat = findViewById(R.id.rvChat);
        rvChat.setHasFixedSize(true);
        rvChat.setLayoutManager(new LinearLayoutManager(this));

        // set adapter to rv
        AdapterForChat adapter = new AdapterForChat(contactMainPage.getChats());

        btnBack = findViewById(R.id.btnBack);
        tvContactName = findViewById(R.id.tvContactName);
        profilePict = findViewById(R.id.profilePic);
        forContactDetails = findViewById(R.id.forContactDetails);

        //Untuk buka detail kontak
        forContactDetails.setOnClickListener(view -> {
            Intent intent = new Intent(ChatActivity.this, ContactDetailsActivity.class);
            intent.putExtra(ChatActivity.EXTRA_CHAT, contactMainPage);
            startActivity(intent);
        });

        //ngambil nama
        tvContactName.setText(contactMainPage.getName());

        //ngambil foto
        Glide.with(getApplicationContext()).load(contactMainPage.getPict()).into(profilePict);

        //for btn
        btnBack.setOnClickListener(view -> finish());

        rvChat.setAdapter(adapter);
    }
}