package com.example.syariffragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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

public class AddFragment extends Fragment {

    EditText edCaption;
    Button btUpload;
    ImageView upImage;
    public static final String Key1 = "capt";

    private static final int RESULT_IMGUPL = 1;
    Uri selectedImage1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);
        edCaption = view.findViewById(R.id.inputCaption);
        btUpload = view.findViewById(R.id.BUpload);
        upImage = view.findViewById(R.id.imageUpl);

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((upImage.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_launcher_background).getConstantState())) {
                    Toast.makeText(getContext(), "Upload Image First", Toast.LENGTH_SHORT).show();
                } else {
                    Uri selectedImage = getActivity().getIntent().getParcelableExtra("image");
                    String caption = edCaption.getText().toString();

                    // create a bundle to pass data to HomeFragment
                    Bundle bundle = new Bundle();
                    bundle.putString(AddFragment.Key1, caption);
                    bundle.putParcelable("image", selectedImage);
                    bundle.putParcelable("img", selectedImage1);

                    // create a new instance of HomeFragment and pass the bundle to it
                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);

                    // start a fragment transaction and replace the current fragment with HomeFragment
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, homeFragment)
                            .commit();
                }
            }
        });

        upImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upIMG = new Intent(Intent.ACTION_PICK);
                upIMG.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(upIMG, RESULT_IMGUPL);

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_IMGUPL && resultCode == RESULT_OK) {

            selectedImage1 = data.getData();
            upImage.setImageURI(selectedImage1);
        }
    }
}
