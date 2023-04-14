package com.example.praktikumrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ContactActivity extends AppCompatActivity {

    private TextView tvName, tvPhone, tvStatus, tvDateOfStatus;
    private ImageView ivPhoto, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        tvName = findViewById(R.id.tv_name);
        tvPhone = findViewById(R.id.tv_phone);
        tvStatus = findViewById(R.id.tv_status);
        tvDateOfStatus = findViewById(R.id.tv_dateOfStatus);
        ivPhoto = findViewById(R.id.iv_photo);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        String name = intent.getStringExtra("varName");
        String phone = intent.getStringExtra("varPhone");
        String status = intent.getStringExtra("varStatus");
        String dateOfStatus = intent.getStringExtra("varDateOfStatus");
        String photo = intent.getStringExtra("varPhoto");

        tvName.setText(name);
        tvPhone.setText(phone);
        tvStatus.setText(status);
        tvDateOfStatus.setText(dateOfStatus);

        Glide.with(ContactActivity.this)
                .load(photo)
                .into(ivPhoto);

        back.setOnClickListener(view -> {
            finish();
        });

        ivPhoto.setOnClickListener(view -> {
            Intent intent1 = new Intent(ContactActivity.this, ProfileActivity.class);
            intent1.putExtra("varName", name);
            intent1.putExtra("varPhoto", photo);

            startActivity(intent1);
        });
    }
}