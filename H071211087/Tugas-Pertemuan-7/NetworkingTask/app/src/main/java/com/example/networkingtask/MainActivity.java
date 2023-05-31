package com.example.networkingtask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private TextView textView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        textView = findViewById(R.id.txtloss);
        button = findViewById(R.id.btnloss);

        Call<ListDataResponse> client = ApiConfig.getApiService().getUsers(1, 12);
        client.enqueue(new Callback<ListDataResponse>() {

            @Override
            public void onResponse(Call<ListDataResponse> call, Response<ListDataResponse>
                    response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        List<UserResponse> userResponseList = response.body().getData();


                        userAdapter = new UserAdapter(userResponseList, MainActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(userAdapter);
                    }
                } else {
                    if (response.body() != null) {

                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<ListDataResponse> call, Throwable t) {

               recyclerView.setVisibility(View.GONE);
               textView.setVisibility(View.VISIBLE);
               button.setVisibility(View.VISIBLE);
               button.setOnClickListener(view -> recreate());

            }
        });
    }
}