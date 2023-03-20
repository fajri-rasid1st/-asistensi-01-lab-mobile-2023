package com.example.kalkulator;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kalkulator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String opr = "0";

    private String operator_input = "";

    private String[] operators = {"-", "+", "x", "/"};

    private ActivityMainBinding binding;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btn0.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "0";
            } else {
                opr = opr + "0";
            }

            displayText(opr);
        });

        binding.btn1.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "1";
            } else {
                opr = opr + "1";
            }
            displayText(opr);
        });

        binding.btn2.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "2";
            } else {
                opr = opr + "2";
            }
            displayText(opr);
        });

        binding.btn3.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "3";
            } else {
                opr = opr + "3";
            }
            displayText(opr);
        });

        binding.btn4.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "4";
            } else {
                opr = opr + "4";
            }
            displayText(opr);
        });

        binding.btn5.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "5";
            } else {
                opr = opr + "5";
            }
            displayText(opr);
        });

        binding.btn6.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "6";
            } else {
                opr = opr + "6";
            }
            displayText(opr);
        });

        binding.btn7.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "7";
            } else {
                opr = opr + "7";
            }
            displayText(opr);
        });

        binding.btn8.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "8";
            } else {
                opr = opr + "8";
            }
            displayText(opr);
        });

        binding.btn9.setOnClickListener(view -> {
            if (opr.equals("0")) {
                opr = "9";
            } else {
                opr = opr + "9";
            }
            displayText(opr);
        });

        binding.btnTambah.setOnClickListener(view -> {
            if (!opr.isEmpty()) {
                if (operator_input.isEmpty()) {
                    opr = opr + operators[1];
                    operator_input = operators[1];
                }
            }

            displayText(opr);
        });

        binding.btnKali.setOnClickListener(view -> {
            if (!opr.isEmpty()) {
                if (operator_input.isEmpty()) {
                    opr = opr + operators[2];
                    operator_input = operators[2];
                }
            }

            displayText(opr);
        });

        binding.btnBagi.setOnClickListener(view -> {
            if (!opr.isEmpty()) {
                if (operator_input.isEmpty()) {
                    opr = opr + operators[3];
                    operator_input = operators[3];
                }
            }

            displayText(opr);
        });

        binding.btnKurang.setOnClickListener(view -> {
            if (!opr.isEmpty()) {
                if (operator_input.isEmpty()) {
                    opr = opr + operators[0];
                    operator_input = operators[0];
                }
            }

            displayText(opr);
        });

        binding.btnDel.setOnClickListener(view -> delete());
        binding.btnAc.setOnClickListener(view -> ac());

        binding.btnHasil.setOnClickListener(view -> {
            if (!operator_input.isEmpty()) {
                String[] tokens = opr.split(operator_input);

                if (tokens.length > 1) {
                    switch (operator_input) {
                        case "+":
                            opr = Double.toString(parseDouble(tokens[0]) + parseDouble(tokens[1]));
                            break;
                        case "-":
                            opr = Double.toString(parseDouble(tokens[0]) - parseDouble(tokens[1]));
                            break;
                        case "x":
                            opr = Double.toString(parseDouble(tokens[0]) * parseDouble(tokens[1]));
                            break;
                        case "/":
                            opr = Double.toString(parseDouble(tokens[0]) / parseDouble(tokens[1]));
                            break;
                    }
                    operator_input = "";
                    displayText(opr);
                }
            }
        });
    }

    public void displayText(String text) {
        binding.operasi.setText(text);
    }

    public void ac() {
        opr = "0";
        operator_input = "";

        displayText(opr);
    }

    public void delete() {
        if (isOperator(opr.substring(opr.length()-1))) {
            operator_input = "";
        }

        if (!opr.equals("0") && opr.length() > 1) {
            opr = opr.substring(0, opr.length() - 1);
        } else {
            opr = "0";
        }

        displayText(opr);
    }

    private boolean isOperator(String text) {
        for (int i = 0; i < operators.length ; i++){
            if (text.equals(operators[i])) return true;
        }

        return false;
    }
}