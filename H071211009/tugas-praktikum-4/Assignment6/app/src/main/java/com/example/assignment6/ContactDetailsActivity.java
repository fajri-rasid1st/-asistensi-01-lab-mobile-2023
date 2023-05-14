package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ContactDetailsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvContactName, tvPhoneNumber, tvStatus, tvStatusDate;
    private ImageView profilePict;

    //untuk intent extra chat
    public static final String EXTRA_CHAT = "extra_chat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        ModelContact contactMainPage = getIntent().getParcelableExtra(EXTRA_CHAT);

        btnBack = findViewById(R.id.btnBack);
        tvContactName = findViewById(R.id.tvContactName);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvStatus = findViewById(R.id.tvStatus);
        tvStatusDate = findViewById(R.id.tvStatusDate);
        profilePict = findViewById(R.id.profilePic);

        //for btn
        btnBack.setOnClickListener(view -> finish());

        //ngambil nama
        tvContactName.setText(contactMainPage.getName());

        //ngambil foto
        Glide.with(getApplicationContext()).load(contactMainPage.getPict()).into(profilePict);

        //lihat detail pict
        profilePict.setOnClickListener(view -> {
            Intent intent = new Intent(ContactDetailsActivity.this, PhotoDetails.class);
            intent.putExtra(ChatActivity.EXTRA_CHAT, contactMainPage);
            startActivity(intent);
        });

        //ngambil nomor
        tvPhoneNumber.setText(contactMainPage.getPhoneNumber());

        //ngambil status
        tvStatus.setText(contactMainPage.getStatus());

        //ngambil tgl status
        tvStatusDate.setText(contactMainPage.getStatusDate());
    }
}