package com.example.assignment4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_FULLNAME = "extra_fullname";
    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_PROFILE = "extra_profile";

    private EditText eCapt;
    ImageView image;
    private Uri uri;

    private final ActivityResultLauncher<Intent> pickImg = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK){
            Intent intent = result.getData();
            if (intent != null){
                uri = intent.getData();
                image.setImageURI(Uri.parse(uri.toString()));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        eCapt = findViewById(R.id.caption);
        image = findViewById(R.id.image);

        uri = Uri.parse("");

    }
    public void image(View view) {
        Intent image = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImg.launch(Intent.createChooser(
                image, "Pick a photo"
        ));
    }

    public void upload(View view) {
        String post = uri.toString();
        String caption = eCapt.getText().toString();
        String fullname = getIntent().getStringExtra(EXTRA_FULLNAME);
        String username = getIntent().getStringExtra(EXTRA_USERNAME);
        String profil = getIntent().getStringExtra(EXTRA_PROFILE);

        if (uri.toString() == "") {
            Toast.makeText(MainActivity2.this, "Pick an image first!", Toast.LENGTH_SHORT).show();
        }else {
            Intent hasil = new Intent(MainActivity2.this, MainActivity3.class);

            hasil.putExtra(MainActivity3.EXTRA_FULLNAME, fullname);
            hasil.putExtra(MainActivity3.EXTRA_USERNAME, username);
            hasil.putExtra(MainActivity3.EXTRA_CAPTION, caption);
            hasil.putExtra(MainActivity3.EXTRA_PROFILE, profil);
            hasil.putExtra(MainActivity3.EXTRA_POST, post);

            startActivity(hasil);
        }

    }
}