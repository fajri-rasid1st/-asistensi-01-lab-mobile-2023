package com.example.activity_assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.activity_assignment_3.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    String hasil = "0";
    String operator = "";
    String[] listOperator = {"+", "-", "÷", "×"};
    String riwayat = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAC.setOnClickListener(view -> clearText());
        binding.buttonC.setOnClickListener(view -> deleteText());
        binding.buttonZero.setOnClickListener(view -> setHasil(binding.buttonZero));
        binding.buttonOne.setOnClickListener(view -> setHasil(binding.buttonOne));
        binding.buttonTwo.setOnClickListener(view -> setHasil(binding.buttonTwo));
        binding.buttonThree.setOnClickListener(view -> setHasil(binding.buttonThree));
        binding.buttonFour.setOnClickListener(view -> setHasil(binding.buttonFour));
        binding.buttonFive.setOnClickListener(view -> setHasil(binding.buttonFive));
        binding.buttonSix.setOnClickListener(view -> setHasil(binding.buttonSix));
        binding.buttonSeven.setOnClickListener(view -> setHasil(binding.buttonSeven));
        binding.buttonEight.setOnClickListener(view -> setHasil(binding.buttonEight));
        binding.buttonNine.setOnClickListener(view -> setHasil(binding.buttonNine));
        binding.buttonPlus.setOnClickListener(view -> setHasil(binding.buttonPlus));
        binding.buttonMinus.setOnClickListener(view -> setHasil(binding.buttonMinus));
        binding.buttonMultiply.setOnClickListener(view -> setHasil(binding.buttonMultiply));
        binding.buttonDivide.setOnClickListener(view -> setHasil(binding.buttonDivide));

        binding.buttonEquals.setOnClickListener(view -> setResult());
    }

    private void setResult() {
        if (!operator.isEmpty() && hasil.length()>1) {
            String[] values = hasil.split(operator.equals("+") ? "\\+" : operator);

            double value1 = Double.parseDouble(values[0]);
            double value2 = Double.parseDouble(values[1]);
            double result = 0;

            if (operator.equals("+")) {
                result = value1 + value2;
            } else if (operator.equals("-")) {
                result = value1 - value2;
            } else if (operator.equals("÷")) {
                result = value1 / value2;
            } else if (operator.equals("×")) {
                result = value1 * value2;
            }

            riwayat = hasil;

            if (canConvertToInt(result)) {
                hasil = String.valueOf((int) result);
                binding.resultTv.setText(hasil);
            } else {
                hasil = String.valueOf(result);
                binding.resultTv.setText(hasil);
            }

            operator = "";
            binding.solutionTv.setText(riwayat);
        }
    }

    private void setHasil(MaterialButton button) {
        String text = button.getText().toString();

        if (isOperator(text)) {
            if (operator.isEmpty()) {
                hasil = hasil + text;
            } else {
                if (isOperator(hasil.substring(hasil.length() - 1))) {
                    hasil = String.format("%s%s", hasil.substring(0, hasil.length() - 1), text);
                }
            }

            operator = text;
        } else {
            if (hasil.equals("0")) {
                hasil = text;
            } else {
                hasil = hasil + text;
            }
        }

        binding.resultTv.setText(hasil);
    }

    private void deleteText() {
        if (isOperator(hasil.substring(hasil.length() - 1))) {
            operator = "";
        }
        if (hasil.length() > 1) {
            hasil = hasil.substring(0, hasil.length() - 1);
        } else {
            hasil = "0";
        }

        binding.resultTv.setText(hasil);
    }

    private void clearText() {
        hasil = "0";
        operator = "";
        riwayat = "";

        binding.resultTv.setText(hasil);
        binding.solutionTv.setText(riwayat);
    }

    private Boolean isOperator(String text) {
        for (int i = 0; i < listOperator.length; i++) {
            if (text.equals(listOperator[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean canConvertToInt(double value) {
        return value - (int) value == 0;
    }
}