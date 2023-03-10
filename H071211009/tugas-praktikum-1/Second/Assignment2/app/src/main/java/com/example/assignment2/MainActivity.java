package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int selected = 0;

    //komponen
    Spinner spinner;
    String[] bgnruang = {"Bola", "Kerucut", "Balok"};
    private TextView tvLabel, tvLabel2, tvLabel3;
    private EditText etText,etText2, etText3;
    private Button btn;
    private TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //untuk ambil komponen
        spinner = findViewById(R.id.bangunruang);

        tvLabel = findViewById(R.id.jari2);
        tvLabel2 = findViewById(R.id.panjang);
        tvLabel3 = findViewById(R.id.lebar);

        etText = findViewById(R.id.nilaijari2);
        etText2 = findViewById(R.id.nilaipanjang);
        etText3 = findViewById(R.id.nilailebar);

        btn = findViewById(R.id.hitung);

        hasil = findViewById(R.id.hasil);

        //spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, bgnruang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = position;

                etText.setError(null);
                etText2.setError(null);
                etText3.setError(null);

                //label text view
                switch (selected){
                    case 0:
                        showFirstInput(true);
                        showSecondInput(false);
                        showThirdInput(false);
                        tvLabel.setText("Jari-Jari");
                        break;
                    case 1:
                        showFirstInput(true);
                        showSecondInput(true);
                        showThirdInput(false);
                        tvLabel.setText("Jari-Jari");
                        tvLabel2.setText("Tinggi");
                        break;
                    case 2:
                        showFirstInput(true);
                        showSecondInput(true);
                        showThirdInput(true);
                        tvLabel.setText("Panjang");
                        tvLabel2.setText("Lebar");
                        tvLabel3.setText("Tinggi");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btn.setOnClickListener(view -> {
            switch (selected){
                case 0:
                    if(isBolaInputValid()) {
                        Double jariJari = Double.parseDouble(etText.getText().toString().trim());
                        calculateBola(jariJari);
                    } else {
                        etText.setError("Field can't be empty");
                    }
                    break;
                case 1:
                    if(isKerucutInputValid()) {
                        Double jariJari = Double.parseDouble(etText.getText().toString().trim());
                        Double tinggi = Double.parseDouble(etText2.getText().toString().trim());
                        calculateKerucut(jariJari, tinggi);
                    } else {
                        etText.setError("Field can't be empty");
                        etText2.setError("Field can't be empty");
                    }
                    break;
                case 2:
                    if(isBalokInputValid()) {
                        Double panjang = Double.parseDouble(etText.getText().toString().trim());
                        Double lebar = Double.parseDouble(etText2.getText().toString().trim());
                        Double tinggi = Double.parseDouble(etText3.getText().toString().trim());
                        calculateBalok(panjang, lebar, tinggi);
                    } else {
                        etText.setError("Field can't be empty");
                        etText2.setError("Field can't be empty");
                        etText3.setError("Field can't be empty");
                    }
                    break;
            }
        });
    }

    // rumus
    private void calculateBalok(Double panjang, Double lebar, Double tinggi) {
        Double result = panjang * lebar * tinggi;
        String formattedResult = String.format("%.2f", result);
        hasil.setText(formattedResult);
    }
    private void calculateKerucut(Double jariJari, Double tinggi) {
        Double result = (Math.PI * Math.pow(jariJari, 2) * tinggi) / 3;
        String formattedResult = String.format("%.2f", result);
        hasil.setText(formattedResult);
    }
    private void calculateBola(Double jariJari) {
        Double result = 4/3 * Math.PI * Math.pow(jariJari, 3);
        String formattedResult = String.format("%.2f", result);
        hasil.setText(formattedResult);
    }

    // method untuk cek edit text apakah kosong atau tidak
    private boolean isBolaInputValid() {
        return !etText.getText().toString().isEmpty();  //negasi(!) untuk membalikkan
    }
    private boolean isKerucutInputValid() {  //bola input valid dan edit text kedua ndk kosong
        return isBolaInputValid() && !etText2.getText().toString().isEmpty();
    }
    private boolean isBalokInputValid() {
        return isKerucutInputValid() && !etText3.getText().toString().isEmpty();
    }

    //method untuk tampilkan input dr edit text n label
    private void showFirstInput(boolean isVisible) {
        if (isVisible){
            tvLabel.setVisibility(View.VISIBLE);
            etText.setVisibility(View.VISIBLE);
        } else {
            tvLabel.setVisibility(View.GONE);
            etText.setVisibility(View.GONE);
        }
    }
    private void showSecondInput(boolean isVisible) {
        if (isVisible){
            tvLabel2.setVisibility(View.VISIBLE);
            etText2.setVisibility(View.VISIBLE);
        } else {
            tvLabel2.setVisibility(View.GONE);
            etText2.setVisibility(View.GONE);
        }
    }
    private void showThirdInput(boolean isVisible) {
        if (isVisible){
            tvLabel3.setVisibility(View.VISIBLE);
            etText3.setVisibility(View.VISIBLE);
        } else {
            tvLabel3.setVisibility(View.GONE);
            etText3.setVisibility(View.GONE);
        }
    }

}