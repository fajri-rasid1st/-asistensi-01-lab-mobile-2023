package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextInputLayout textInputLayout;
    private TextInputLayout textInputLayout2;
    private TextInputLayout textInputLayout3;
    private Button button;
    private TextView textView;

    int selectedValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        textInputLayout3 = findViewById(R.id.textInputLayout3);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.result);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.bangun_ruang, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();

                selectedValue = spinner.getSelectedItemPosition();

                switch (selectedValue) {
                    case 0:
                        showBolaInputs();
                        return;
                    case 1:
                        showKerucutInputs();
                        return;
                    case 2:
                        showBalokInputs();
                        return;
                }

                textView.setText("Hasil");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(
                view -> {
                    switch (selectedValue) {
                        case 0:
                            calculateBola();
                            return;
                        case 1:
                            calculateKerucut();
                            return;
                        case 2:
                            calculateBalok();
                            return;
                    }
                }
        );
    }

    private void calculateBola() {
        Double result = 0.0;
        textView.setText("Hasil");

        if (TextUtils.isEmpty(textInputLayout.getEditText().getText().toString())) {
            textInputLayout.setError("Tidak Boleh Kosong");
        }else {
            Double value1 = Double.parseDouble(textInputLayout.getEditText().getText().toString());
            Double calcBola = 22.0 / 7.0 * value1 * value1 * value1 * 4.0 / 3.0;
            textView.setText(String.valueOf(calcBola));
        }
        // 1. hitung volume bola
        // 2. masukkan ke variable result
        // 3. ganti text hasil ke result

        // textView.setText();
    }

    private void calculateKerucut() {
        Double result = 0.0;
        textView.setText("Hasil");

        if (TextUtils.isEmpty(textInputLayout.getEditText().getText().toString()) && TextUtils.isEmpty(textInputLayout2.getEditText().getText().toString())) {
            textInputLayout.setError("Tidak Boleh Kosong");
            textInputLayout2.setError("Tidak Boleh Kosong");


        } else if (TextUtils.isEmpty(textInputLayout.getEditText().getText().toString())) {
            textInputLayout.setError("Tidak Boleh Kosong");

        } else if (TextUtils.isEmpty(textInputLayout2.getEditText().getText().toString())) {
            textInputLayout2.setError("Tidak Boleh Kosong");

        } else {
            Double value1 = Double.parseDouble(textInputLayout.getEditText().getText().toString());
            Double value2 = Double.parseDouble(textInputLayout2.getEditText().getText().toString());
            Double calcCone = 22.0 / 7.0 * value1 * value1 * value2 * 1.0 / 3.0;
            textView.setText(String.valueOf(calcCone));
        }

        // 1. hitung volume bola
        // 2. masukkan ke variable result
        // 3. ganti text hasil ke result

        // textView.setText();
    }

    private void calculateBalok() {
        Double result = 0.0;

        if (TextUtils.isEmpty(textInputLayout.getEditText().getText().toString()) && TextUtils.isEmpty(textInputLayout2.getEditText().getText().toString()) && TextUtils.isEmpty(textInputLayout3.getEditText().getText().toString())) {
            textInputLayout.setError("Tidak Boleh Kosong");
            textInputLayout2.setError("Tidak Boleh Kosong");
            textInputLayout3.setError("Tidak Boleh Kosong");

        } else if (TextUtils.isEmpty(textInputLayout2.getEditText().getText().toString())) {
            textInputLayout2.setError("Tidak Boleh Kosong");
        } else if (TextUtils.isEmpty(textInputLayout3.getEditText().getText().toString())) {
            textInputLayout3.setError("Tidak Boleh Kosong");

        }else {
            Double value1 = Double.parseDouble(textInputLayout.getEditText().getText().toString());
            Double value2 = Double.parseDouble(textInputLayout2.getEditText().getText().toString());
            Double value3 = Double.parseDouble(textInputLayout3.getEditText().getText().toString());
            Double calcBalok = value3*value2*value1;
            textView.setText(String.valueOf(calcBalok));
        }
        // 1. hitung volume bola
        // 2. masukkan ke variable result
        // 3. ganti text hasil ke result

        // textView.setText();
    }

    private void showBolaInputs() {
        showSecondInput(false);
        showThirdInput(false);
        textInputLayout.setHint("jari jari");
    }


    private void showKerucutInputs() {
        showSecondInput(true);
        showThirdInput(false);
        textInputLayout.setHint("Jari jari");
        textInputLayout2.setHint("Tinggi");
    }

    private void showBalokInputs() {
        showSecondInput(true);
        showThirdInput(true);
        textInputLayout.setHint("Panjang");
        textInputLayout2.setHint("Lebar");
        textInputLayout3.setHint("Tinggi");
    }

    private void showSecondInput(boolean isVisible) {
        if (isVisible) {
            textInputLayout2.setVisibility(View.VISIBLE);
        } else {
            textInputLayout2.setVisibility(View.GONE);
        }
    }

    private void showThirdInput(boolean isVisible) {
        if (isVisible) {
            textInputLayout3.setVisibility(View.VISIBLE);
        } else {
            textInputLayout3.setVisibility(View.GONE);
        }
    }
}