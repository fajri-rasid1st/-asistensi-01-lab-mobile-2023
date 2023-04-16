package com.example.task4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    TextInputEditText edUser,edName;
    TextInputLayout lUser,lName;
    CircleImageView uImage;
    Button sButton;
    private static final int RESULT_IMG = 1;

    Uri selectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUser = (TextInputEditText) findViewById(R.id.inputUserEdit);
        edName = (TextInputEditText) findViewById(R.id.inputNameEdit);
        lName = (TextInputLayout) findViewById(R.id.inputLayoutName);
        lUser = (TextInputLayout) findViewById(R.id.inputLayoutUser);
        uImage = (CircleImageView) findViewById(R.id.profile_image);
        sButton = (Button) findViewById(R.id.BSubmit);

        sButton.setOnClickListener(view -> {
            if (TextUtils.isEmpty(edUser.getText().toString()) && TextUtils.isEmpty(edName.getText().toString()) && uImage.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_launcher_background).getConstantState()){
                Toast.makeText(this, "Upload image first", Toast.LENGTH_SHORT).show();
                edUser.setError("Required");
                edName.setError("Required");

            } else if (TextUtils.isEmpty(edUser.getText().toString()) && TextUtils.isEmpty(edName.getText().toString())) {
                edUser.setError("Required");
                edName.setError("Required");
            } else if (uImage.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_launcher_background).getConstantState() && TextUtils.isEmpty(edName.getText().toString())) {
                Toast.makeText(this, "Upload image first", Toast.LENGTH_SHORT).show();
                edName.setError("Required");
            }else if (uImage.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_launcher_background).getConstantState() && TextUtils.isEmpty(edUser.getText().toString())) {
                Toast.makeText(this, "Upload image first", Toast.LENGTH_SHORT).show();
                edUser.setError("Required");
            }else if (uImage.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_launcher_background).getConstantState()) {
                Toast.makeText(this, "Upload image first", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(edUser.getText().toString())) {
                edUser.setError("Required");
            } else if (TextUtils.isEmpty(edName.getText().toString())) {
                edName.setError("Required");
            }else {
                Intent KePost = new Intent(getApplicationContext(), PostActivity.class);
                String user = edUser.getText().toString();
                String name = edName.getText().toString();
                KePost.putExtra("Uname" , user);
                KePost.putExtra("Fname" , name);
                KePost.putExtra("image" ,selectedImage);
                startActivity(KePost);


            }
        });

        uImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uplImage = new Intent(Intent.ACTION_PICK);
                uplImage.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(uplImage,RESULT_IMG);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_IMG && resultCode == RESULT_OK){

            selectedImage = data.getData();
            uImage.setImageURI(selectedImage);
        }
    }
}