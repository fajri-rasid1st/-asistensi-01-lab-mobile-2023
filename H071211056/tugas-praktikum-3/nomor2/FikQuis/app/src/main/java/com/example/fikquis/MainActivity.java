package com.example.fikquis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private CircleImageView img;
    EditText name;
    Button btnPLay;
    int SELECT_IMAGE_CODE = 1;
    Uri uri;
    Intent intent;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        name = findViewById(R.id.name);
        btnPLay = findViewById(R.id.apply);

        btnPLay.setOnClickListener(view -> {
                String iName = name.getText().toString();
                if (iName.isEmpty()){
                    name.setError("Isi Terlebih Dahulu");
                }else{
                    Intent intent = new Intent(MainActivity.this, ShowScore.class);
                    intent.putExtra("img", uri);
                    intent.putExtra("inputName", iName);
                    startActivity(intent);
                }
        });

        img.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            uri = data.getData();
            data.getData();
            img.setImageURI(uri);
        }
    }
}

