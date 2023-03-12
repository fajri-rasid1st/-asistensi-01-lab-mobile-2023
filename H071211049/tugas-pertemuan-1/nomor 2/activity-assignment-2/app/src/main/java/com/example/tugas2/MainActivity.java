package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner pilihan;

    Button hitung;

    TextView textView, textView2, textView3, hasil;

    EditText editText, editText2, editText3;

    int selectedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pilihan = findViewById(R.id.pilihan);

        hitung = findViewById(R.id.hitung);

        hasil = (TextView) findViewById(R.id.hasil);

        textView = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv3);

        editText = findViewById(R.id.et1);
        editText2 = findViewById(R.id.et2);
        editText3 = findViewById(R.id.et3);

        pilihan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hasil.setText("Hasil");

                selectedItem = position;

                switch (selectedItem) {
                    case 0:
                        showBolaInputs();
                        break;
                    case 1:
                        showKerucutInputs();
                        break;
                    case 2:
                        showBalokInputs();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItem == 0) {
                    if (isBolaInputEmpty()) {
                        editText.setError("harap diisi");
                    } else {
                        Double jariJari = Double.parseDouble(editText.getText().toString());
                        Double volume = 4.0 / 3.0 * Math.PI * Math.pow(jariJari, 3);
                        hasil.setText(String.format("%.2f", volume));
                    }
                } else if (selectedItem == 1) {
                    if (isKerucutInputEmpty()) {
                        editText.setError("harap diisi");
                        editText2.setError("harap diisi");
                    } else {
                        Double jariJari = Double.parseDouble(editText.getText().toString());
                        Double tinggi = Double.parseDouble(editText2.getText().toString());
                        Double volume = 1.0 / 3.0 * Math.PI * Math.pow(jariJari, 2) * tinggi;
                        hasil.setText(String.format("%.2f", volume));

                    }
                } else {
                    if (isBalokInputEmpty()) {
                        editText.setError("harap diisi");
                        editText2.setError("harap diisi");
                        editText3.setError("harap diisi");
                    } else {
                        Double panjang = Double.parseDouble(editText.getText().toString());
                        Double lebar = Double.parseDouble(editText2.getText().toString());
                        Double tinggi = Double.parseDouble(editText3.getText().toString());
                        Double volume = panjang * lebar * tinggi;
                        hasil.setText(String.format("%.2f", volume));

                    }
                }

            }
        });
    }

    private boolean isBolaInputEmpty() {
        return editText.getText().toString().trim().isEmpty();
    }

    private boolean isKerucutInputEmpty() {
        return isBolaInputEmpty() && editText2.getText().toString().trim().isEmpty();
    }

    private boolean isBalokInputEmpty() {
        return isKerucutInputEmpty() && editText3.getText().toString().trim().isEmpty();
    }

    private void showBalokInputs() {
        showSecondInput(true);
        showThirdInput(true);
        textView.setText("Panjang");
        textView2.setText("Tinggi");
        textView3.setText("Lebar");
    }

    private void showKerucutInputs() {
        showSecondInput(true);
        showThirdInput(false);
        textView.setText("Jari-jari");
        textView2.setText("Tinggi");
    }

    private void showBolaInputs() {
        showSecondInput(false);
        showThirdInput(false);
        textView.setText("Jari-jari");
    }

    private void showSecondInput(boolean isVisible) {
        if (isVisible) {
            textView2.setVisibility(View.VISIBLE);
            editText2.setVisibility(View.VISIBLE);
        } else {
            textView2.setVisibility(View.GONE);
            editText2.setVisibility(View.GONE);
        }
    }

    private void showThirdInput(boolean isVisible) {
        if (isVisible) {
            textView3.setVisibility(View.VISIBLE);
            editText3.setVisibility(View.VISIBLE);
        } else {
            textView3.setVisibility(View.GONE);
            editText3.setVisibility(View.GONE);
        }
    }
}