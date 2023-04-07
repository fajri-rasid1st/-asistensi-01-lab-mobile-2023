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

public class MainActivity extends AppCompatActivity {

    private EditText eFullName, eUsername;
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
        setContentView(R.layout.activity_main);

        eFullName = findViewById(R.id.fullname);
        eUsername = findViewById(R.id.username);
        image = findViewById(R.id.profilePic);

        uri = Uri.parse("");
    }

    public void submit(View view) {
        String fullname = eFullName.getText().toString();
        String username = eUsername.getText().toString();
        String profile = uri.toString();

        if (uri.toString() == "") {
            Toast.makeText(MainActivity.this, "Please pick a photo profile first", Toast.LENGTH_SHORT).show();
        } else if (fullname.isEmpty()){
            Toast.makeText(MainActivity.this, "Fill the data first!", Toast.LENGTH_SHORT).show();
        } else if (username.isEmpty()){
            Toast.makeText(MainActivity.this, "Fill the data first!", Toast.LENGTH_SHORT).show();
        } else {
            Intent next = new Intent(MainActivity.this, MainActivity2.class);

            next.putExtra(MainActivity2.EXTRA_FULLNAME, fullname);
            next.putExtra(MainActivity2.EXTRA_USERNAME, username);
            next.putExtra(MainActivity2.EXTRA_PROFILE, profile);
            startActivity(next);
        }
    }

    public void image(View view) {
        Intent image = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImg.launch(Intent.createChooser(
                image, "Pick a photo"
        ));
    }
}