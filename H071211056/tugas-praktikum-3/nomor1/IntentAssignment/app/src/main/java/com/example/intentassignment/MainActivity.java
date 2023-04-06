package com.example.intentassignment;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private CircleImageView imgProfil;
    EditText fullname, username;
    Button btnSub;
    int SELECT_IMAGE_CODE = 1;
    Uri uri1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgProfil = findViewById(R.id.profile_image);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        btnSub = findViewById(R.id.btnSub);

        btnSub.setOnClickListener(view -> {
            String fName = fullname.getText().toString();
            String uName = username.getText().toString();

            if (fName.isEmpty()){
                fullname.setError("Harus DiIsi");
            }else if (uName.isEmpty()){
                username.setError("Harus Diisi");
            }else if (uri1 == null){
                Toast.makeText(this, "Upload image first", Toast.LENGTH_SHORT).show();
            } else{
                Intent intent = new Intent(MainActivity.this, PostCaption.class);
                intent.putExtra("imgProf", uri1);
                intent.putExtra("fullname", fName);
                intent.putExtra("username", uName);
                startActivity(intent);
            }
        });


        imgProfil.setOnClickListener(view -> {
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
            uri1 = data.getData();
            data.getData();
            imgProfil.setImageURI(uri1);
        }
    }
}
