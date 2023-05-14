package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.Post;
import com.example.myapplication.models.User;

import java.io.IOException;

public class AddFragment extends Fragment {
    private ActivityResultLauncher<Intent> launcher;
    private MainActivity mainActivity;

    private User user;
    private ImageView mImageView;

    private EditText mCaption;
    private Button mChooseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent resultIntent = result.getData();
                        if (resultIntent != null){
                            Uri imgUri = resultIntent.getData();

                            user.getPost().setPhotoUri(imgUri);

                            Glide.with(container.getRootView().getContext())
                                    .load(user.getPost().getPhotoUri())
                                    .into(mImageView);
                        }
                    }
                }
        );

        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView = view.findViewById(R.id.img);
        mChooseButton = view.findViewById(R.id.btn);
        mCaption = view.findViewById(R.id.Et);

        mainActivity = ((MainActivity) getActivity());

        user = new User("Muhammad Fikri", "Fikri", R.drawable.doffy, new Post());

        mImageView.setOnClickListener(v -> {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(Intent.createChooser(pickPhoto, "Choose photo"));
        });

        mChooseButton.setOnClickListener(v -> {
            if (user.getPost().getPhotoUri() == null){
                Toast.makeText(getActivity(),"Masukkan gambar dulu", Toast.LENGTH_SHORT).show();
                return;
            }

            String caption = mCaption.getText().toString().trim();
            user.getPost().setCaption(caption);

            mCaption.setText("");
            mImageView.setImageURI(null);

            HomeFragment.adapterData.addUser(user);

            mainActivity.navigateFragment(new HomeFragment());

            Toast.makeText(mainActivity,"Berhasil", Toast.LENGTH_SHORT).show();
        });
    }
}

