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


public class MainActivity extends AppCompatActivity {

    private ImageView profileImage;

    private TextView fullname, user;

    private Uri imageUri;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent resultIntent = result.getData();

                    if (resultIntent != null) {
                        imageUri = resultIntent.getData();
                        profileImage.setImageURI(imageUri);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = findViewById(R.id.btn_submit);
        fullname = findViewById(R.id.name);
        user = findViewById(R.id.username);
        profileImage = (ImageView) findViewById(R.id.profile_img);

        submit.setOnClickListener(view -> {
            String fullName = fullname.getText().toString();
            String userName = user.getText().toString();

            if (imageUri != null) {
                if (fullName.isEmpty() && userName.isEmpty()) {
                    fullname.setError("Field can't be empty!");
                    user.setError("Field can't be empty!");
                } else if (fullName.isEmpty()) {
                    fullname.setError("Field can't be empty!");
                } else if (userName.isEmpty()) {
                    user.setError("Field can't be empty!");
                } else {
                    Intent post = new Intent(MainActivity.this, MainActivity2.class);
                    post.putExtra("keyfullname", fullName);
                    post.putExtra("keyusername", userName);
                    post.putExtra("keyprofile", imageUri);
                    startActivity(post);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please pick a photo profile first!", Toast.LENGTH_SHORT).show();
            }
        });

        profileImage.setOnClickListener(view -> {

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(Intent.createChooser(gallery, "Pilih Photo Profile"));

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
//                profileImage.setImageBitmap(bitmap);
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}