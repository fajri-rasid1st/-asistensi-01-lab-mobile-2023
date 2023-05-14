package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatapp.Models.UserModel;

public class ImgViewerActivity extends AppCompatActivity {

    UserModel userModel;
    ImageView ivBack, ivFoto;
    TextView tvNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_viewer);

        userModel = getIntent().getParcelableExtra("user", UserModel.class);

        ivBack = findViewById(R.id.ivBack);
        ivFoto = findViewById(R.id.ivFoto);
        tvNama = findViewById(R.id.tvNama);

        tvNama.setText(userModel.getNama());
        ivFoto.setImageResource(userModel.getProfil());

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
    }
}