package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.User;

public class ProfileFragment extends Fragment {
    private User user;

    private TextView fullName, nickName;
    private ImageView image;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fullName = view.findViewById(R.id.fullName);
        nickName = view.findViewById(R.id.nickName);
        image = view.findViewById(R.id.profile_image);

        if (user != null) {
            Glide.with(requireContext()).load(user.getImg()).into(image);
            fullName.setText(user.getFullName());
            nickName.setText(user.getUserName());
        }
    }
}
