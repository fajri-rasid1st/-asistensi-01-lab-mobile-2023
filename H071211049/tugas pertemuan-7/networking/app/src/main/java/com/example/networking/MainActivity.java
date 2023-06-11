package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    TextView trefresh;
    ImageView refresh;
    ProgressBar pbar;
    int count = 0;
    Timer timer;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        trefresh = findViewById(R.id.trefresh);
        refresh = findViewById(R.id.refresh);
        pbar = findViewById(R.id.pbar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layoutManager);

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count++;
                pbar.setProgress(count);
                if (count==50) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 0, 100);

        fetchData();
        if (!isConnected()) {
            trefresh.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.VISIBLE);
        }
    }

    public void fetchData() {
        showload();
        Call<ListResponse> listResponseCall = ApiConfig.getApiService().getUser("12");
        listResponseCall.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<User> users = response.body().getUsers();
                        Adapter adapter = new Adapter(MainActivity.this, users);
                        rv.setHasFixedSize(true);
                        rv.setAdapter(adapter);
                        hideload();
                    } else {
                        if (response.body() != null) {
                            Log.e("MainActivity", "onFailure: " + response.message());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public void hideload() {
        rv.setVisibility(View.VISIBLE);
        pbar.setVisibility(View.INVISIBLE);
    }

    public void showload() {
        pbar.setVisibility(View.VISIBLE);
    }
}