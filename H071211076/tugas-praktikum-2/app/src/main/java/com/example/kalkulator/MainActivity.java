package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kalkulator.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String[] operators = {"x", "-", "+", "/", "%"};
    private String operator = "";
    private String value = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnC.setOnClickListener(view -> reset());
        binding.btnDelete.setOnClickListener(view -> del());

        binding.btn0.setOnClickListener(view -> setValue(binding.btn0));
        binding.btn1.setOnClickListener(view -> setValue(binding.btn1));
        binding.btn2.setOnClickListener(view -> setValue(binding.btn2));
        binding.btn3.setOnClickListener(view -> setValue(binding.btn3));
        binding.btn4.setOnClickListener(view -> setValue(binding.btn4));
        binding.btn5.setOnClickListener(view -> setValue(binding.btn5));
        binding.btn6.setOnClickListener(view -> setValue(binding.btn6));
        binding.btn7.setOnClickListener(view -> setValue(binding.btn7));
        binding.btn8.setOnClickListener(view -> setValue(binding.btn8));
        binding.btn9.setOnClickListener(view -> setValue(binding.btn9));
        binding.btnPlus.setOnClickListener(view -> setValue(binding.btnPlus));
        binding.btnMinus.setOnClickListener(view -> setValue(binding.btnMinus));
        binding.btnDivide.setOnClickListener(view -> setValue(binding.btnDivide));
        binding.btnMultiply.setOnClickListener(view -> setValue(binding.btnMultiply));
        binding.btnPercent.setOnClickListener(view -> setValue(binding.btnPercent));
        binding.btnEquals.setOnClickListener(view -> {
            final String regex = Objects.equals(operator, "+") ? "\\+" : operator;

            final String[] values = value.split(regex);

            if (values.length > 1) {
                calculate(operator, Double.parseDouble(values[0]), Double.parseDouble(values[1]));
            }
        });
    }

    private void calculate(String operator, double value1, double value2) {
        double hasil;

        switch (operator) {

            case "+":
                hasil = value1 + value2;
                value = String.valueOf(hasil);

                if (value.endsWith(".0")) {
                    value = value.replace(".0", "");
                    binding.resultTv.setText(value);
                } else {
                    binding.resultTv.setText(value);
                }

                break;

            case "x":
                hasil = value1 * value2;
                value = String.valueOf(hasil);

                if (value.endsWith(".0")) {
                    value = value.replace(".0", "");
                    binding.resultTv.setText(value);
                } else {
                    binding.resultTv.setText(value);
                }
                break;
            case "-":
                hasil = value1 - value2;
                value = String.valueOf(hasil);

                if (value.endsWith(".0")) {
                    value = value.replace(".0", "");
                    binding.resultTv.setText(value);
                } else {
                    binding.resultTv.setText(value);
                }
                break;
            case "/":
                hasil = value1 / value2;
                value = String.valueOf(hasil);

                if (value.endsWith(".0")) {
                    value = value.replace(".0", "");
                    binding.resultTv.setText(value);
                } else {
                    binding.resultTv.setText(value);
                }
                break;
            case "%":
                hasil = value1 % value2;
                value = String.valueOf(hasil);

                if (value.endsWith(".0")) {
                    value = value.replace(".0", "");
                    binding.resultTv.setText(value);
                } else {
                    binding.resultTv.setText(value);
                }

                break;
        }

        operator = "";
    }

    private void reset() {
        value = "0";
        operator = "";
        binding.resultTv.setText(value);
    }

    private void del() {
        if (isOperator(value.substring(value.length() - 1))) {
            operator = "";
        }

        if (value.length() > 1) {
            value = value.substring(0, value.length() - 1);
        } else {
            value = "0";
        }

        binding.resultTv.setText(value);
    }

    private void setValue(Button button) {
        final String btnText = button.getText().toString().trim();

        if (isOperator(btnText)) {
            if (operator.isEmpty()) {
                value = value + btnText;
            } else {
                if (isOperator(value.substring(value.length() - 1))) {
                    value = String.format(Locale.getDefault(), "%s%s", value.substring(0, value.length() - 1), btnText);
                }
            }

            operator = btnText;
        } else {
            if (Objects.equals(value, "0")) {
                value = btnText;
            } else {
                value = value + btnText;
            }
        }

        binding.resultTv.setText(value);
    }

    private boolean isOperator(String value) {
        return Arrays.stream(operators).anyMatch(value::contains);
    }
}