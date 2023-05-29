package com.example.background_thread_assignment.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.background_thread_assignment.R;
import com.example.background_thread_assignment.data.model.User;
import com.google.android.material.imageview.ShapeableImageView;

public class ProfileFragment extends Fragment {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShapeableImageView photoProfile = view.findViewById(R.id.profilePhoto);
        TextView fullname = view.findViewById(R.id.text_fullname);
        TextView username = view.findViewById(R.id.text_username);

        photoProfile.setImageResource(user.getProfilePicture());
        fullname.setText(user.getFullname());
        username.setText(user.getUsername());
    }
}