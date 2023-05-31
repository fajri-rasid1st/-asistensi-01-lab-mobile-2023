package com.example.tprak7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;


import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_viewer);

        Handler handler = new Handler();
        int id = getIntent().getIntExtra("id", 0);
        Call<DataResponse> call = ApiConfig.getApiService().getUser(id);

        MaterialCardView materialCardView = findViewById(R.id.mcvProfileViewer);
        ShimmerFrameLayout shimmerFrameLayout = findViewById(R.id.sflProfileViewer);
        MaterialTextView tvFullName = findViewById(R.id.tvFullName);
        MaterialTextView tvEmail = findViewById(R.id.tvEmail);
        CircleImageView civUserPic = findViewById(R.id.civUserPic);
        MaterialTextView materialTextView = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);

        materialCardView.setVisibility(View.INVISIBLE);
        tvEmail.setVisibility(View.INVISIBLE);
        tvFullName.setVisibility(View.INVISIBLE);
        civUserPic.setVisibility(View.INVISIBLE);

        shimmerFrameLayout.startShimmerAnimation();

        handler.postDelayed(() -> {

            materialCardView.setVisibility(View.VISIBLE);
            tvEmail.setVisibility(View.VISIBLE);
            tvFullName.setVisibility(View.VISIBLE);
            civUserPic.setVisibility(View.VISIBLE);

            shimmerFrameLayout.setVisibility(View.INVISIBLE);

            shimmerFrameLayout.stopShimmerAnimation();

            call.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(@NonNull Call<DataResponse> call, @NonNull Response<DataResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        DataResponse dataResponse = response.body();
                        UserResponse userResponse = dataResponse.getData();

                        tvFullName.setText(userResponse.getFirstName() + " " + userResponse.getLastName());
                        tvEmail.setText(userResponse.getEmail());
                        Glide.with(ProfileViewerActivity.this)
                                .load(userResponse.getAvatarUrl())
                                .into(civUserPic);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DataResponse> call, @NonNull Throwable t) {

                    materialCardView.setVisibility(View.INVISIBLE);
                    materialTextView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);

                    button.setOnClickListener(v -> recreate());
                }
            });
        }, 1300);
    }
}