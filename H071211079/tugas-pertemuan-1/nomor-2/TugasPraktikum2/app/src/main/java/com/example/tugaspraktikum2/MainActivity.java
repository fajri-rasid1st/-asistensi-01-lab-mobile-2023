package com.example.tugaspraktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] item = {"Bola", "Kerucut", "Balok"};
    TextView label1, label2, label3, hasil;
    EditText editText1, editText2, editText3;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner list = findViewById(R.id.listItem);

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, item);
        list.setAdapter(adapter);

        label1 = findViewById(R.id.labelView1);
        label2 = findViewById(R.id.labelView2);
        label3 = findViewById(R.id.labelView3);

        editText1 = findViewById(R.id.editTextNumber1);
        editText2 = findViewById(R.id.editTextNumber2);
        editText3 = findViewById(R.id.editTextNumber3);

        btn1 = findViewById(R.id.button1);
        hasil = findViewById(R.id.hasil);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem == "Kerucut") {
                    label3.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                    label2.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                    label1.setText("Jari-jari");
                    label3.setText("Tinggi");
                    hasil.setText("Hasil");
                } else if (selectedItem == "Balok") {
                    label2.setVisibility(View.VISIBLE);
                     editText2.setVisibility(View.VISIBLE);
                    label3.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                    label1.setText("Panjang");
                    label2.setText("Lebar");
                    label3.setText("Tinggi");
                    hasil.setText("Hasil");
                } else {
                    label2.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                    label3.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);
                    label1.setText("Jari-jari");
                    hasil.setText("Hasil");
                }
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();
                String text3 = editText3.getText().toString();

                String selectedItem = list.getSelectedItem().toString();
                double nilai1, nilai2, nilai3, hasil;
                if (selectedItem == "Bola") {
                    if (text1.isEmpty()) {
                        editText1.setError("Harap Isi");
                    } else {
                        nilai1 = Double.parseDouble(editText1.getText().toString());
                        hasil = (4.00 / 3.00) * Math.PI * Math.pow(nilai1, 3);

                        MainActivity.this.hasil.setText(String.format("%.2f", hasil));
                    }
                } else if (selectedItem == "Kerucut") {
                    if (text1.isEmpty() && text3.isEmpty()) {
                        editText1.setError("Harap Isi");
                        editText3.setError("Harap Isi");
                    } else if (text1.isEmpty()) {
                        editText1.setError("Harap Isi");
                    } else if (text3.isEmpty()) {
                        editText3.setError("Harap Isi");
                    } else {
                        nilai1 = Double.parseDouble(editText1.getText().toString());
                        nilai3 = Double.parseDouble(editText3.getText().toString());
                        hasil = (Math.PI * nilai1 * nilai1 * nilai3) / 3.00;

                        MainActivity.this.hasil.setText(String.format("%.2f", hasil));
                    }
                } else {
                    if (text1.isEmpty() && text2.isEmpty() && text3.isEmpty()) {
                        editText1.setError("Harap Isi");
                        editText2.setError("Harap Isi");
                        editText3.setError("Harap Isi");
                    } else if (text1.isEmpty() && text2.isEmpty()) {
                        editText1.setError("Harap Isi");
                        editText2.setError("Harap Isi");
                    } else if (text2.isEmpty() && text3.isEmpty()) {
                        editText2.setError("Harap Isi");
                        editText3.setError("Harap Isi");
                    } else if (text3.isEmpty() && text1.isEmpty()) {
                        editText3.setError("Harap Isi");
                        editText1.setError("Harap Isi");
                    } else if (text1.isEmpty()) {
                        editText1.setError("Harap Isi");
                    } else if (text2.isEmpty()) {
                        editText2.setError("Harap Isi");
                    } else if (text3.isEmpty()) {
                        editText3.setError("Harap Isi");
                    } else {
                        nilai1 = Double.parseDouble(editText1.getText().toString());
                        nilai2 = Double.parseDouble(editText2.getText().toString());
                        nilai3 = Double.parseDouble(editText3.getText().toString());
                        hasil = nilai1 * nilai2 * nilai3;

                        MainActivity.this.hasil.setText(String.format("%.2f", hasil));
                    }
                }
            }
        });


    }
}