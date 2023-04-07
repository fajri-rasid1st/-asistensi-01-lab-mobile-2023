package com.example.tprak3_2rev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView nilai, over, best;
    private Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int skor =  Integer.parseInt(getIntent().getStringExtra("skor"));
        String nama = getIntent().getStringExtra("nama");

        over = findViewById(R.id.over);
        nilai = findViewById(R.id.textView2);
        btnRestart = findViewById(R.id.button);
        Player player = getIntent().getParcelableExtra("user");

        if (skor > player.getBestSkor()) {
            player.setBestSkor(skor);
        }


        over.setText("Game Over, " + player.getNama());
        nilai.setText(String.valueOf(skor));
        btnRestart.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfilActivity.class);
            intent.putExtra("player", player);
            startActivity(intent);
        });
    }

//    private void restart(Player player) {
//        Intent intent = new Intent(this, ProfilActivity.class);
//        intent.putExtra("player", player);
//        startActivity(intent);
//    }
}