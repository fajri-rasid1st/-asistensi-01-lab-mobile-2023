package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView calculation;
    Boolean hasOperation = false;

    String operation = "";

    String text = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation = findViewById(R.id.calculation);
    }

    public void number(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                if (Objects.equals(text, "0")) {
                    text = "0";
                } else {
                    text += "0";
                }
                break;
            case R.id.btn1:
                if (Objects.equals(text, "0")) {
                    text = "1";
                } else {
                    text += "1";
                }
                break;
            case R.id.btn2:
                if (Objects.equals(text, "0")) {
                    text = "2";
                } else {
                    text += "2";
                }
                break;
            case R.id.btn3:
                if (Objects.equals(text, "0")) {
                    text = "3";
                } else {
                    text += "3";
                }
                break;
            case R.id.btn4:
                if (Objects.equals(text, "0")) {
                    text = "4";
                } else {
                    text += "4";
                }
                break;
            case R.id.btn5:
                if (Objects.equals(text, "0")) {
                    text = "5";
                } else {
                    text += "5";
                }
                break;
            case R.id.btn6:
                if (Objects.equals(text, "0")) {
                    text = "6";
                } else {
                    text += "6";
                }
                break;
            case R.id.btn7:
                if (Objects.equals(text, "0")) {
                    text = "7";
                } else {
                    text += "7";
                }
                break;
            case R.id.btn8:
                if (Objects.equals(text, "0")) {
                    text = "8";
                } else {
                    text += "8";
                }
                break;
            case R.id.btn9:
                if (Objects.equals(text, "0")) {
                    text = "9";
                } else {
                    text += "9";
                }
                break;
        }

        calculation.setText(text);
    }

    public void operation(View view) {
        if (!hasOperation) {
            switch (view.getId()) {
                case R.id.btnBagi:
                    operation = "/";
                    text += "/";
                    break;
                case R.id.btnKali:
                    operation = "x";
                    text += "x";
                    break;
                case R.id.btnKurang:
                    operation = "-";
                    text += "-";
                    break;
                case R.id.btnTambah:
                    operation = "+";
                    text += "+";
                    break;
            }

            hasOperation = true;
        } else {
            switch (view.getId()) {
                case R.id.btnBagi:
                    operation = "/";
                    break;
                case R.id.btnKali:
                    operation = "x";
                    break;
                case R.id.btnKurang:
                    operation = "-";
                    break;
                case R.id.btnTambah:
                    operation = "+";
                    break;
            }

            text = String.format("%s%s", text.substring(0, text.length() - 1), operation);
        }

        calculation.setText(text);
    }

    public void AC(View view) {
        text = "0";
        operation = "";
        hasOperation = false;
        calculation.setText(text);
    }

    public void delete(View view) {
        StringBuilder stringBuilder = new StringBuilder(calculation.getText());
        stringBuilder.deleteCharAt(calculation.getText().length() - 1);
        String del = stringBuilder.toString();

        if (del.equals("")) {
            text = "0";
            operation = "";
            hasOperation = false;
            calculation.setText(text);
        } else {
            calculation.setText(del);
        }
    }

    public void result(View view) {
        if (hasOperation && !operation.isEmpty()) {
            String[] values = text.split(Objects.equals(operation, "+") ? "\\+" : operation);

            double value1 = Double.parseDouble(values[0]);
            double value2 = Double.parseDouble(values[1]);

            double result = 0;

            switch (operation) {
                case "/":
                    try {
                        result = value1 / value2;
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "x":
                    try {
                        result = value1 * value2;
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "-":
                    try {
                        result = value1 - value2;
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "+":
                    try {
                        result = value1 + value2;
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

            text = String.valueOf(result);

            calculation.setText(text);

            operation = "";
            hasOperation = false;
        }
    }
}