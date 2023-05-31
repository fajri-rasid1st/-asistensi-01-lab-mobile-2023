package com.example.tprak6.profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tprak6.R;
import com.example.tprak6.data.model.User;
import com.facebook.shimmer.ShimmerFrameLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_viewer);

        Handler handler = new Handler();
        User user = getIntent().getParcelableExtra("user");
        CircleImageView civProfile = findViewById(R.id.civProfile);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvUsername = findViewById(R.id.tvUsername);
        ShimmerFrameLayout sfl = findViewById(R.id.sfl);

        civProfile.setImageResource(user.getProfilePic());
        tvName.setText(user.getName());
        tvUsername.setText(user.getUsername());

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
    }
}