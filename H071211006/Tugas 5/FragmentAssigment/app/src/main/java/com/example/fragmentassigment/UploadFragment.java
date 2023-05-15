package com.example.fragmentassigment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;

public class UploadFragment extends Fragment {

    private EditText etCaption;
    private ImageView ivUpload;
    private Button btnUpload;
    private Uri imageUri;
    private ArrayList<DataPost> dataList = new ArrayList();
    ActivityResultLauncher<Intent> launcher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etCaption = view.findViewById(R.id.et_caption);
        ivUpload = view.findViewById(R.id.iv_upload);
        btnUpload = view.findViewById(R.id.btn_upload);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent resultIntent = result.getData();

                        if (resultIntent != null) {
                            imageUri = resultIntent.getData();
                            ivUpload.setImageURI(imageUri);
                        }
                    }
                }
        );

        ivUpload.setOnClickListener(view1 -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(Intent.createChooser(gallery, "Pilih Photo!"));
        });

        btnUpload.setOnClickListener(view1 -> {
            String capt = etCaption.getText().toString();
            dataList.add(new DataPost(capt, imageUri));

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("key_post", dataList);

            Intent intent = getActivity().getIntent();
            intent.putExtras(bundle);

            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction().replace(R.id.frameContainer, homeFragment).commit();
            Toast.makeText(getActivity(), "Post Success", Toast.LENGTH_SHORT).show();
            etCaption.getText().clear();

        });
    }
}