package com.example.assignment7;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    ImageView ivAddImg;
    EditText etCaption;
    Button btnUpload;
    private ArrayList<PostModel> posts = new ArrayList<>();

    //manggil img dan capt di model
    PostModel postModel;

    //img
    private ActivityResultLauncher<Intent> pickImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pickImg = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            Uri uri = intent.getData();

                            postModel.setImage(uri);
                            ivAddImg.setImageURI(postModel.getImage());
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
        postModel = new PostModel();

        //img
        ivAddImg.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImg.launch(Intent.createChooser(intent, "Choose a photo!"));
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capt = etCaption.getText().toString();

                if (postModel.getImage() != null) {
                    postModel.setCaption(capt);
                    posts.add(postModel);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("posts", posts);

                    Intent intent = getActivity().getIntent();
                    intent.putExtras(bundle);

                    ivAddImg.setImageURI(null);
                    etCaption.setText("");

                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);
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