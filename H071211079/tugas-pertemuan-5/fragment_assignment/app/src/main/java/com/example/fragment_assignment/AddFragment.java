package com.example.fragment_assignment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
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

import java.util.ArrayList;


public class AddFragment extends Fragment {

    String postUri;
    ImageView photoUpload;
    ActivityResultLauncher<Intent> launcherIntentPhotos;
    private ArrayList<PostModel> posts = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText addCaption = view.findViewById(R.id.addCaption);
        photoUpload = view.findViewById(R.id.photoUpload);
        Button btn_upload = view.findViewById(R.id.btn_upload);



        launcherIntentPhotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->  {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        postUri = uri.toString();
                        photoUpload.setImageURI(uri);
                    }
                }
        );

        btn_upload.setOnClickListener(view1 -> {
            String cpt = addCaption.getText().toString();
            posts.add(new PostModel(cpt, "@dhiyaaunnisa_", "Dhiyaa Unnisa", Uri.parse(postUri)));
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("keyPost", posts);
            // Intent intent = getActivity().getIntent();
            // intent.putExtras(bundle);
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
            addCaption.getText().clear();
        });

        photoUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launcherIntentPhotos.launch(Intent.createChooser(intent, "Pilih gambar"));
            }
        });

    }
}