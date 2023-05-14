package com.example.rv_assignment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageActivity extends AppCompatActivity {

    public static final String EXTRA_CHAT = "extra_chat";
    private TextView name;
    private ImageView profilePict;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        name = findViewById(R.id.name);
        profilePict = findViewById(R.id.profilePict);
        backButton = findViewById(R.id.backArrow);

        ModelClass modelClass = getIntent().getParcelableExtra(EXTRA_CHAT);

        name.setText(modelClass.getName());
        Glide.with(ImageActivity.this)
                .load(modelClass.getProfilePict())
                .apply(new RequestOptions().override(350, 350))
                .into(profilePict);

        backButton.setOnClickListener(view -> finish());
    }

}
