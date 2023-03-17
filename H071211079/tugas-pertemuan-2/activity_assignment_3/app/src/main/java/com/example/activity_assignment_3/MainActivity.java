package com.example.activity_assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.activity_assignment_3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private String currentValue = "0";
    private String operator = "";
    private String[] operators = {"+", "-", "/", "x"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnClear.setOnClickListener(view -> clearText());
        binding.btnDelete.setOnClickListener(view -> deleteText());

        binding.btn0.setOnClickListener(view -> setTextOnClick(binding.btn0));
        binding.btn1.setOnClickListener(view -> setTextOnClick(binding.btn1));
        binding.btn2.setOnClickListener(view -> setTextOnClick(binding.btn2));
        binding.btn3.setOnClickListener(view -> setTextOnClick(binding.btn3));
        binding.btn4.setOnClickListener(view -> setTextOnClick(binding.btn4));
        binding.btn5.setOnClickListener(view -> setTextOnClick(binding.btn5));
        binding.btn6.setOnClickListener(view -> setTextOnClick(binding.btn6));
        binding.btn7.setOnClickListener(view -> setTextOnClick(binding.btn7));
        binding.btn8.setOnClickListener(view -> setTextOnClick(binding.btn8));
        binding.btn9.setOnClickListener(view -> setTextOnClick(binding.btn9));
        binding.btnDivider.setOnClickListener(view -> setTextOnClick(binding.btnDivider));
        binding.btnMultiply.setOnClickListener(view -> setTextOnClick(binding.btnMultiply));
        binding.btnMinus.setOnClickListener(view -> setTextOnClick(binding.btnMinus));
        binding.btnPlus.setOnClickListener(view -> setTextOnClick(binding.btnPlus));
        binding.btnEquals.setOnClickListener(view -> calculate());
    }

    private void calculate() {
        String[] values = currentValue.split(operator.equals("+") ? "\\+" : operator);

        double value1 = Double.parseDouble(values[0]);
        double value2 = Double.parseDouble(values[1]);
        double result = 0;

        if (operator.equals("+")) {
            result = value1 + value2;
        } else if (operator.equals("-")) {
            result = value1 - value2;
        } else if (operator.equals("x")) {
            result = value1 * value2;
        } else if (operator.equals("/")) {
            result = value1 / value2;
        }

        currentValue = String.valueOf(result);

        operator = "";

        binding.tvResult.setText(currentValue);
    }

    private void clearText() {
        currentValue = "0";
        operator = "";
        binding.tvResult.setText(currentValue);
    }

    private void deleteText() {
        if (isOperator(currentValue.substring(currentValue.length() - 1))) {
            operator = "";
        }

        if (currentValue.length() > 1) {
            currentValue = currentValue.substring(0, currentValue.length() - 1);
        } else {
            currentValue = "0";
        }

        binding.tvResult.setText(currentValue);
    }

    private boolean isOperator(String value) {
        for (int i = 0; i < operators.length; i++) {
            if (value.equals(operators[i])) return true;
        }

        return false;
    }

    private void setTextOnClick(Button button) {
        String value = button.getText().toString();

        if (isOperator(value)) {
            if (!operator.isEmpty()) {
                if (isOperator(currentValue.substring(currentValue.length() - 1))) {
                    currentValue = String.format("%s%s", currentValue.substring(0, currentValue.length() - 1), value);
                }
            } else {
                currentValue += value;
            }

            operator = value;
        } else {
            if (currentValue.equals("0")) {
                currentValue = value;
            } else {
                currentValue += value;
            }
        }

        binding.tvResult.setText(currentValue);
    }
}