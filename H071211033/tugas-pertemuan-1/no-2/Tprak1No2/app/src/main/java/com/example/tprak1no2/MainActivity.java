package com.example.tprak1no2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int selectedItem = 0;
    private TextView hasil, textView, textView2, textView3;
    private Button hitung;
    private EditText editText, editText2, editText3;


    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasil = findViewById(R.id.hasil);
        hitung = findViewById(R.id.hitung);

        editText = findViewById(R.id.panjang);
        editText2 = findViewById(R.id.lebar);
        editText3 = findViewById(R.id.tinggi);

        textView = findViewById(R.id.textView3);
        textView2 = findViewById(R.id.textView4);
        textView3 = findViewById(R.id.textView5);

        spinner = findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ruang, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();

                hasil.setText("Hasil");
                editText.setText("");
                editText2.setText("");
                editText3.setText("");

                selectedItem = spinner.getSelectedItemPosition();

                if (selectedItem == 1) {
                    textView.setText("Jari-jari");
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText("Tinggi");
                    textView3.setVisibility(View.GONE);
                    editText2.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.GONE);
                } else if (selectedItem == 0) {
                    textView.setText("Panjang");
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText("Lebar");
                    textView3.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                } else {
                    textView.setText("Jari-jari");
                    textView2.setVisibility(View.GONE);
                    textView3.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItem == 0) {
                    boolean cond1 = isFirstInputValid();
                    boolean cond2 = isSecondInputValid();
                    boolean cond3 = isThirdInputValid();

                    if (cond1 && cond2 && cond3) {
                        double p = Double.parseDouble(editText.getText().toString());
                        double l = Double.parseDouble(editText2.getText().toString());
                        double t = Double.parseDouble(editText3.getText().toString());
                        double jumlah = p * l * t;
                        hasil.setText(String.format("%.2f", jumlah));
                    }
                } else if (selectedItem == 1) {
                    boolean cond1 = isFirstInputValid();
                    boolean cond2 = isSecondInputValid();

                    if (cond1 && cond2) {
                        double r = Double.parseDouble(editText.getText().toString());
                        double t = Double.parseDouble(editText2.getText().toString());
                        double jumlah = (Math.PI * Math.pow(r, 2) * t) / 3;
                        hasil.setText(String.format("%.2f", jumlah));
                    }
                } else {
                    if (isFirstInputValid()) {
                        double r = Double.parseDouble(editText.getText().toString());
                        double jumlah = (4 * Math.PI * Math.pow(r, 3)) / 3;
                        hasil.setText(String.format("%.2f", jumlah));
                    }
                }
            }
        });
    }

    private boolean isFirstInputValid() {
        boolean isValid = !editText.getText().toString().trim().isEmpty();

        if (!isValid) editText.setError("Harus diisi");

        return isValid;
    }

    private boolean isSecondInputValid() {
        boolean isValid = !editText2.getText().toString().trim().isEmpty();

        if (!isValid) editText2.setError("Harus diisi");

        return isValid;
    }

    private boolean isThirdInputValid() {
        boolean isValid = !editText3.getText().toString().trim().isEmpty();

        if (!isValid) editText3.setError("Harus diisi");

        return isValid;
    }
}