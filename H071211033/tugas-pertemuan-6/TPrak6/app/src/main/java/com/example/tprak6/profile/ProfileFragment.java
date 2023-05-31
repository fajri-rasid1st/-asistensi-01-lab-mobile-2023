package com.example.tprak6.profile;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tprak6.R;
import com.example.tprak6.data.model.User;
import com.example.tprak6.search.SearchFragment;
import com.example.tprak6.home.HomeFragment;
import com.example.tprak6.upload.UploadFragment;
import com.facebook.shimmer.ShimmerFrameLayout;

import de.hdodenhof.circleimageview.CircleImageView;

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

        ShimmerFrameLayout sfl = view.findViewById(R.id.sfl);
        ImageButton btnUpload = view.findViewById(R.id.btnUpload);
        ImageButton btnHome = view.findViewById(R.id.btnHome);
        ImageButton btnSearch = view.findViewById(R.id.btnSearch);
        CircleImageView civProfile = view.findViewById(R.id.civProfile);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvUsername = view.findViewById(R.id.tvUsername);

        Handler handler = new Handler();

        civProfile.setVisibility(View.INVISIBLE);
        tvName.setVisibility(View.INVISIBLE);
        tvUsername.setVisibility(View.INVISIBLE);
        sfl.startShimmerAnimation();

        handler.postDelayed(()->{
            civProfile.setImageResource(user.getProfilePic());
            tvName.setText(user.getName());
            tvUsername.setText(user.getUsername());

            civProfile.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
            tvUsername.setVisibility(View.VISIBLE);
            sfl.stopShimmerAnimation();
            sfl.setVisibility(View.INVISIBLE);
        }, 1350);

        btnUpload.setOnClickListener(v -> {
            UploadFragment uploadFragment = new UploadFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.cl, uploadFragment);
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