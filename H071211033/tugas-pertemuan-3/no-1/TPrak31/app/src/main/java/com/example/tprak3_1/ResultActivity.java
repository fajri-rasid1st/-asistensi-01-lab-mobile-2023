package com.example.tprak3_1;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultActivity extends AppCompatActivity {
    private ImageView imageView2;
    private CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textView1 = findViewById(R.id.username);
        TextView textView2 = findViewById(R.id.name);
        TextView textView3 = findViewById(R.id.caption);

        circleImageView = findViewById(R.id.profile);
        imageView2 = findViewById(R.id.post);

        String imageUri1Str = getIntent().getStringExtra("imageUri1Str");
        Uri imageUri1 = Uri.parse(imageUri1Str);
        String imageUri2Str = getIntent().getStringExtra("imageUri2");
        Uri imageUri2 = Uri.parse(imageUri2Str);

        circleImageView.setImageURI(imageUri1);
        imageView2.setImageURI(imageUri2);


        String username = getIntent().getStringExtra("username");
        String name = getIntent().getStringExtra("name");
        String caption = getIntent().getStringExtra("caption");

        textView1.setText(username);
        textView2.setText(name);
        textView3.setText(caption);
    }



}