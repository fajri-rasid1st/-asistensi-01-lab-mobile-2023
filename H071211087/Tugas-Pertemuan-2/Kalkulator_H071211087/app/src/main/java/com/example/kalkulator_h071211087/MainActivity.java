package com.example.kalkulator_h071211087;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.kalkulator_h071211087.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    String process;
    ActivityMainBinding binding;


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLayout.btnHapus.setOnClickListener(view -> {
            binding.inputLayout.angkaMasuk.setText("");
            binding.inputLayout.angkaKeluar.setText("");
        });


        binding.buttonLayout.btn0.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "0");
        });

        binding.buttonLayout.btn1.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "1");
        });

        binding.buttonLayout.btn2.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "2");
        });

        binding.buttonLayout.btn3.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "3");
        });

        binding.buttonLayout.btn4.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "4");
        });

        binding.buttonLayout.btn5.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "5");
        });

        binding.buttonLayout.btn6.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "6");
        });

        binding.buttonLayout.btn7.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "7");
        });

        binding.buttonLayout.btn8.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "8");
        });

        binding.buttonLayout.btn9.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "9");
        });

        binding.buttonLayout.btnTambah.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "+");
        });


        binding.buttonLayout.btnKurang.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "-");
        });

        binding.buttonLayout.btnKali.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "x");
        });

        binding.buttonLayout.btnBagi.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "/");
        });

        binding.buttonLayout.btnTitik.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + ".");
        });

        binding.buttonLayout.btnPersen.setOnClickListener(view -> {
            process = binding.inputLayout.angkaMasuk.getText().toString();
            binding.inputLayout.angkaMasuk.setText(process + "%");
        });

        binding.buttonLayout.btnBackspace.setOnClickListener(view -> {
            String word = binding.inputLayout.angkaMasuk.getText().toString();
            int input = word.length();
            if (input > 0) {
                binding.inputLayout.angkaMasuk.setText(word.substring(0, input - 1));
            }
        });


        binding.buttonLayout.btnHasil.setOnClickListener(view -> {
            String[] operators = {"%", "/", "x", "-", "+"};
            String selectedOperator = "";

            process = binding.inputLayout.angkaMasuk.getText().toString();

            String[] arrProcess = {};

            for (String operator : operators) {
                if (process.split(operator).length < 1) {
                    selectedOperator = operator;
                    arrProcess = process.split(operator);
                }
            }

            double value1 = Double.parseDouble(arrProcess[0]);
            double value2 = Double.parseDouble(arrProcess[2]);
            double result;


            switch (selectedOperator) {
                case "x":
                    result = value1 * value2;

                    binding.inputLayout.angkaKeluar.setText(String.format("%f", result));

                case "+":
                     result = value1 + value2;

                    binding.inputLayout.angkaKeluar.setText(String.format("%f", result));

                case "-":
                    result = value1 - value2;

                    binding.inputLayout.angkaKeluar.setText(String.format("%f", result));

                case "/":
                    result = value1 / value2;

                    binding.inputLayout.angkaKeluar.setText(String.format("%f", result));

                case "%":
                    result = value1 % value2;

                    binding.inputLayout.angkaKeluar.setText(String.format("%f", result));
            }

        });
    }
}