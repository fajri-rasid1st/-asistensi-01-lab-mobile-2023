package com.example.syarifquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView scre, stat;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        scre = findViewById(R.id.score);
        stat = findViewById(R.id.status);
        submit = findViewById(R.id.Rbutton);

        Intent keEnd = getIntent();
        String Score = keEnd.getStringExtra(MainActivity.Key1);
        String Stats = getIntent().getStringExtra("status");
        scre.setText(Score);
        stat.setText(Stats);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent KeStart = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(KeStart);
            }
        });



    }
}