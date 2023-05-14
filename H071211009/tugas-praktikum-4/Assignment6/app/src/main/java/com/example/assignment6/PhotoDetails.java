package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PhotoDetails extends AppCompatActivity {

    ImageView profilePict;
    TextView tvContactName;
    private ImageButton btnBack;

    //untuk intent extra chat (yg mau dikirim)
    public static final String EXTRA_CHAT = "extra_chat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        profilePict = findViewById(R.id.iv_pict);
        tvContactName = findViewById(R.id.tvContactName);
        btnBack = findViewById(R.id.btnBack);

        ModelContact contactMainPage = getIntent().getParcelableExtra(EXTRA_CHAT);

        tvContactName.setText(contactMainPage.getName());

        btnBack.setOnClickListener(view -> finish());

        //ngambil foto
        Glide.with(getApplicationContext()).load(contactMainPage.getPict()).into(profilePict);
    }
}