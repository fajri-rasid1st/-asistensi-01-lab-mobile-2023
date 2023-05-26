package com.example.tprak7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<ListDataResponse> call = ApiConfig.getApiService().getUsers(1, 12);
        List<UserResponse> userResponses = new ArrayList<>();
        Handler handler = new Handler();

        MaterialToolbar materialToolbar = findViewById(R.id.materialToolbar);
        ShimmerFrameLayout sfl = findViewById(R.id.sfl);
        RecyclerView rvUser = findViewById(R.id.rvUser);
        MaterialTextView materialTextView = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);

        setSupportActionBar(materialToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Networking");
        getSupportActionBar().setIcon(R.drawable.baseline_wifi_24);

        rvUser.setVisibility(View.INVISIBLE);
        sfl.startShimmerAnimation();

        handler.postDelayed(() -> {

            rvUser.setVisibility(View.VISIBLE);
            sfl.setVisibility(View.GONE);

            sfl.stopShimmerAnimation();

            call.enqueue(new Callback<ListDataResponse>() {
                @Override
                public void onResponse(@NonNull Call<ListDataResponse> call, @NonNull Response<ListDataResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ListDataResponse listDataResponse = response.body();
                        userResponses.addAll(listDataResponse.getData());
                    }

                    userAdapter = new UserAdapter(userResponses);
                    rvUser.setHasFixedSize(true);
                    rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvUser.setAdapter(userAdapter);
                }

                @Override
                public void onFailure(@NonNull Call<ListDataResponse> call, @NonNull Throwable t) {

                    materialTextView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);

                    button.setOnClickListener(v -> recreate());
                }
            });
        }, 3000);
    }
}