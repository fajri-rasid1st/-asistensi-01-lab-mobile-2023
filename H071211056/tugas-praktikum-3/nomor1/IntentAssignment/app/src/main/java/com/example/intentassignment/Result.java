package com.example.intentassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Result extends AppCompatActivity {
    public static final String EXTRA_CAP = "imgCap";
    TextView use, full, cap;
    ImageView iCap, iProf;
    String fName, uName, caps;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        use = findViewById(R.id.username);
        full = findViewById(R.id.fullname);
        cap = findViewById(R.id.caption);
        iCap = findViewById(R.id.imgCap);
        iProf = findViewById(R.id.profile_image);

        Bundle bundle = getIntent().getExtras();
        caps = bundle.getString("caption");
        fName = bundle.getString("fullname");
        uName = bundle.getString("username");
        Uri uri = (Uri) getIntent().getParcelableExtra("img");
        Uri uri1 = (Uri) getIntent().getParcelableExtra("imgProf");
        iCap.setImageURI(uri);
        iProf.setImageURI(uri1);
        cap.setText(caps);
        full.setText(fName);
        use.setText(uName);
    }
}