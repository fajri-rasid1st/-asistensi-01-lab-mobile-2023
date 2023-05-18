package com.example.tprak5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class UploadFragment extends Fragment {

    Post post;
    ImageView ivPost;
    EditText etCaption;
    Button btnUpload;
    ImageButton btnProfile;
    ImageButton btnHome;
    MainActivity mainActivity;

    //fungsi untuk upload
    public void upload() {

        //jika tidak ada foto terpilih saat menekan tombol, akan muncul error toast
        if (post.getImgaeUri() == null) {

            Toast.makeText(getActivity(), "Please set a post image", Toast.LENGTH_SHORT).show();

            return;
        }

        String caption = etCaption.getText().toString();

        post.setCaption(caption);

        etCaption.setText("");
        ivPost.setImageURI(null);

        //menambahkan objek ke adapter
        HomeFragment.postAdapter.addPost(post);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.cl, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        post = new Post();
        ivPost = view.findViewById(R.id.ivPost);
        etCaption = view.findViewById(R.id.etCaption);
        btnUpload = view.findViewById(R.id.btnUpload);
        btnProfile = view.findViewById(R.id.btnProfile);
        btnHome = view.findViewById(R.id.btnHome);

        mainActivity = (MainActivity) getActivity();

        //mengambil data uri dari aplikasi eksternal
        ActivityResultLauncher<Intent> getContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

            if (result.getResultCode() == Activity.RESULT_OK) {

                Intent intent = result.getData();

                if (intent != null) {

                    Uri imageUri = intent.getData();
                    post.setImgaeUri(imageUri);

                    ivPost.setImageURI(post.getImgaeUri());
                }
            }
        });

        ivPost.setOnClickListener(v -> {

            //memilih tipe data media dari aplikasi eksternal
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            getContent.launch(Intent.createChooser(intent, "Choose an image"));
        });

        btnUpload.setOnClickListener(v -> upload());

        btnProfile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnHome.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, homeFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}