package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatapp.Models.UserModel;

public class ProfilActivity extends AppCompatActivity {

    UserModel userModel;
    ImageView ivBack, ivFoto;
    TextView tvNama, tvNomor, tvStatus, tvTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        userModel = getIntent().getParcelableExtra("user", UserModel.class);
        ivBack = findViewById(R.id.ivBack);
        ivFoto = findViewById(R.id.ivFoto);
        tvNama = findViewById(R.id.tvNama);
        tvNomor = findViewById(R.id.tvNomor);
        tvStatus = findViewById(R.id.tvStatus);
        tvTanggal = findViewById(R.id.tvTanggalStatus);

        ivFoto.setImageResource(userModel.getProfil());
        tvNama.setText(userModel.getNama());
        tvNomor.setText(userModel.getNomor());
        tvStatus.setText(userModel.getStatus());
        tvTanggal.setText(userModel.getTanggal());

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

        ivFoto.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilActivity.this, ImgViewerActivity.class);
            intent.putExtra("user", userModel);
            startActivity(intent);

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        ivBack.setOnClickListener(view -> finish());
    }
}