package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatapp.Adapters.ChatsAdapter;
import com.example.chatapp.DataSource.ChatsDataSource;
import com.example.chatapp.Models.UserModel;


public class ChatActivity extends AppCompatActivity {

    ConstraintLayout clProfil;
    RecyclerView rvChat;
    UserModel userModel;
    ImageView ivBack, ivProfil;
    TextView tvNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l);

        userModel = getIntent().getParcelableExtra("user", UserModel.class);
        clProfil = findViewById(R.id.clProfil);
        rvChat = findViewById(R.id.rvChat);
        ivBack = findViewById(R.id.ivBack);
        ivProfil = findViewById(R.id.ivFoto);
        tvNama = findViewById(R.id.tvNama);

        rvChat.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvChat.setLayoutManager(layoutManager);
        rvChat.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ivProfil.setImageResource(userModel.getProfil());
        tvNama.setText(userModel.getNama());

        ivBack.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView view = (ImageView) v;
                    view.getDrawable().setColorFilter(new PorterDuffColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP));
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    ImageView view = (ImageView) v;
                    view.getDrawable().setColorFilter(null);
                    view.invalidate();
                    v.performClick();
                    break;
                }
            }
            return false;
        });

        ivBack.setOnClickListener(view -> finish());
        clProfil.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfilActivity.class);
            intent.putExtra("user", userModel);
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l);
        });
        ivProfil.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfilActivity.class);
            intent.putExtra("user", userModel);
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l);
        });

        setUpAdapter();
    }

    private void setUpAdapter() {
        rvChat.setAdapter(new ChatsAdapter(ChatsDataSource.getChatsList()));
    }
}