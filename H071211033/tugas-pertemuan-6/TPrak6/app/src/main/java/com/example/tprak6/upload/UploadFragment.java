package com.example.tprak6.upload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tprak6.MainActivity;
import com.example.tprak6.data.model.Post;
import com.example.tprak6.profile.ProfileFragment;
import com.example.tprak6.R;
import com.example.tprak6.search.SearchFragment;
import com.example.tprak6.data.model.User;
import com.example.tprak6.home.HomeFragment;

public class UploadFragment extends Fragment {

    User user;
    ImageView ivPost;
    EditText etCaption;
    Button btnUpload;
    ImageButton btnProfile;
    ImageButton btnHome;
    MainActivity mainActivity;
    public void upload() {

        if (user.getPost().getImgaeUri() == null) {

            Toast.makeText(getActivity(), "Please set a post image", Toast.LENGTH_SHORT).show();

            return;
        }

        String caption = etCaption.getText().toString();

        user.getPost().setCaption(caption);

        etCaption.setText("");
        ivPost.setImageURI(null);
        user.setName("Muhammad Al Fudhail");
        user.setUsername("alfdhail");
        user.setProfilePic(R.drawable.profile);

        HomeFragment.dataSource.addUser(user);

        Toast.makeText(getActivity(), "Post success", Toast.LENGTH_LONG).show();

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

        user = new User();
        user.setPost(new Post());
        ivPost = view.findViewById(R.id.ivPost);
        etCaption = view.findViewById(R.id.etCaption);
        btnUpload = view.findViewById(R.id.btnUpload);
        btnProfile = view.findViewById(R.id.btnProfile);
        btnHome = view.findViewById(R.id.btnHome);
        ImageButton btnSearch = view.findViewById(R.id.btnSearch);

        mainActivity = (MainActivity) getActivity();

        ActivityResultLauncher<Intent> getContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

            if (result.getResultCode() == Activity.RESULT_OK) {

                Intent intent = result.getData();

                if (intent != null) {

                    Uri imageUri = intent.getData();
                    user.getPost().setImgaeUri(imageUri);

                    ivPost.setImageURI(user.getPost().getImgaeUri());
                }
            }
        });

        ivPost.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            getContent.launch(Intent.createChooser(intent, "Choose an image"));
        });

        btnUpload.setOnClickListener(v -> upload());

        btnProfile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();

            profileFragment.setUser(mainActivity.getUser());

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnSearch.setOnClickListener(v -> {
            SearchFragment searchFragment = new SearchFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, searchFragment);
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