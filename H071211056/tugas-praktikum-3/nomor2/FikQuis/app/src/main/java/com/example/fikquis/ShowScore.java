package com.example.fikquis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowScore extends AppCompatActivity {

    private CircleImageView img;
    TextView name;
    Button btnPLay;
    String iName;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        name = findViewById(R.id.name);
        btnPLay = findViewById(R.id.play);
        img = findViewById(R.id.img);

        Bundle bundle = getIntent().getExtras();
        iName = bundle.getString("inputName");
        Uri uri = (Uri) getIntent().getParcelableExtra("img");
        img.setImageURI(uri);
        name.setText(iName);

        btnPLay.setOnClickListener(view -> {
            String name = getIntent().getStringExtra("inputName");
            Intent intent = new Intent(ShowScore.this, Questions2.class);
            intent.putExtra("inputName", name);
            startActivity(intent);
        });

    }
}