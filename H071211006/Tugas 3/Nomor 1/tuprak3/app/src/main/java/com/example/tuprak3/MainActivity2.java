package com.example.tuprak3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {

    private ImageView postImage;
    private TextView readCaption;
    private Uri imageUri;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent resultIntent = result.getData();

                    if (resultIntent != null) {
                        imageUri = resultIntent.getData();
                        postImage.setImageURI(imageUri);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button upload = findViewById(R.id.btn_upload);
        readCaption = findViewById(R.id.caption);
        postImage = (ImageView) findViewById(R.id.post_img);

        upload.setOnClickListener(view -> {
            String readcaption = readCaption.getText().toString();
            String userName = getIntent().getStringExtra("keyusername");
            String fullName = getIntent().getStringExtra("keyfullname");
            Uri profile = getIntent().getParcelableExtra("keyprofile");

            if (imageUri != null) {
                Intent post = new Intent(MainActivity2.this, MainActivity3.class);

                post.putExtra("keypost", imageUri);
                post.putExtra("keycaption", readcaption);
                post.putExtra("keyfullname", fullName);
                post.putExtra("keyusername", userName);
                post.putExtra("keyprofile", profile);

                startActivity(post);
            }else {
                Toast.makeText(getApplicationContext(), "Please pick a photo profile first!", Toast.LENGTH_SHORT).show();
            }
        });


        postImage.setOnClickListener(view -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(Intent.createChooser(gallery, "Pilih Photo!"));
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
//            imageUri = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                postImage.setImageBitmap(bitmap);
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}