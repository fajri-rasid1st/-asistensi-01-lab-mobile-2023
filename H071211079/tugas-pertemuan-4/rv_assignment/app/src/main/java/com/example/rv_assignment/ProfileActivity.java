package com.example.rv_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity  {

    ArrayList<ModelClass2> List;
    public static final String EXTRA_CHAT = "extra_chat";
    private TextView userName, userNumber, userStatus, date;
    private ImageView imageView;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_activity);

        userName = findViewById(R.id.userName);
        userNumber = findViewById(R.id.userNumber);
        userStatus = findViewById(R.id.userStatus);
        date = findViewById(R.id.date);
        imageView = findViewById(R.id.imageView);
        backButton = findViewById(R.id.backArrow);
        List = new ArrayList<>();

        ModelClass modelClass = getIntent().getParcelableExtra(EXTRA_CHAT);

        userName.setText(modelClass.getUserName());
        userNumber.setText(modelClass.getUserNumber());
        userStatus.setText(modelClass.getUserStatus());
        date.setText(modelClass.getDate());
        Glide.with(ProfileActivity.this)
                .load(modelClass.getProfilePict())
                .apply(new RequestOptions().override(350, 350))
                .into(imageView);

        backButton.setOnClickListener(view -> finish());

        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, ImageActivity.class);
            intent.putExtra(ImageActivity.EXTRA_CHAT, modelClass);
            startActivity(intent);
        });
    }
}

