package com.example.assignment8;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    ImageView ivAddImg;
    EditText etCaption;
    Button btnUpload;
    Uri uri;
    private ArrayList<ModelPost> posts = new ArrayList<>();

    //img
    private ActivityResultLauncher<Intent> pickImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Mengambil gambar dari galeri
        pickImg = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            uri = intent.getData();
                            ivAddImg.setImageURI(uri);
                        }
                    }
                });

        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivAddImg = view.findViewById(R.id.ivAddImg);
        etCaption = view.findViewById(R.id.etCaption);
        btnUpload = view.findViewById(R.id.btnUpload);

        // Mengatur OnClickListener untuk ImageView ivAddImg
        ivAddImg.setOnClickListener(view1 -> {
            // Membuat Intent untuk memilih gambar dari galeri
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImg.launch(Intent.createChooser(intent, "Choose a photo!"));
        });

        // Mengatur OnClickListener untuk btnUpload
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uri != null) {
                    // Mengambil caption dari EditText etCaption
                    String capt = etCaption.getText().toString();

                    // Menambahkan objek ModelPost baru ke dalam ArrayList posts
                    DataSource.posts.add(new ModelPost(capt, "001","Emilia Damayanti", "https://i.pinimg.com/564x/7c/85/2a/7c852aa963add0762d3500ea4d5c6874.jpg",String.valueOf(uri)));

                    uri = null;

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("posts", posts);

                    // Mendapatkan Intent dari activity yang meng-host fragment ini
                    Intent intent = getActivity().getIntent();
                    intent.putExtras(bundle);

                    // Mengosongkan tampilan ivAddImg dan etCaption
                    ivAddImg.setImageURI(null);
                    etCaption.setText("");

                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);

                    // Mengganti fragment saat ini dengan HomeFragment
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, homeFragment)
                            .commit();
                    Toast.makeText(getContext(), "Upload Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Select an image first!", Toast.LENGTH_SHORT).show();
                }
                etCaption.getText().clear();
            }
        });
    }
}