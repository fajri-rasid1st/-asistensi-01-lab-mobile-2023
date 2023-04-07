package com.example.tprak3_2rev;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Player player;
    private TextView tvPertanyaan;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    Integer skor = 0;
    Integer jumPertanyaan = Qna.pertanyaan.length;
    Integer currPertanyaanIndex = 0;
    String pilihJawaban = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvJumPertanyaan = findViewById(R.id.textView);
        tvPertanyaan = findViewById(R.id.textView2);

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        Button btnSubmit = findViewById(R.id.button5);
        player = getIntent().getParcelableExtra("user");

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);


        tvJumPertanyaan.setText("Total Pertanyaan: " + jumPertanyaan);

        pertanyaanBaru();

    }

    private void pertanyaanBaru() {
        if (currPertanyaanIndex < jumPertanyaan) {
            tvPertanyaan.setText(Qna.pertanyaan[currPertanyaanIndex]);
            button1.setText(Qna.pilihan[currPertanyaanIndex][0]);
            button2.setText(Qna.pilihan[currPertanyaanIndex][1]);
            button3.setText(Qna.pilihan[currPertanyaanIndex][2]);
            button4.setText(Qna.pilihan[currPertanyaanIndex][3]);
        } else if (currPertanyaanIndex.equals(jumPertanyaan)) {

            tamat();
        }

    }

    private void tamat() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("skor", skor.toString());
        intent.putExtra("user", player);
        startActivity(intent);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        Button klikBtn = (Button) view;
        button1.setBackgroundColor(Color.parseColor("#28A0FF"));
        button2.setBackgroundColor(Color.parseColor("#28A0FF"));
        button3.setBackgroundColor(Color.parseColor("#28A0FF"));
        button4.setBackgroundColor(Color.parseColor("#28A0FF"));

        if (klikBtn.getId() == R.id.button5) {

            if (pilihJawaban.equals(Qna.jawaban[currPertanyaanIndex])) {
                skor+=20;
            }

            currPertanyaanIndex++;
            pertanyaanBaru();
        } else {
            pilihJawaban = klikBtn.getText().toString();
            klikBtn.setBackgroundColor(Color.parseColor("#15A70B"));
        }
    }
}