package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment3.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    String[] operators = {"+", "-", "x", "/"};

    String selectedOperator = "";
    String value = "0"; // nampung isi result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnClear.setOnClickListener(view -> clearClick());
        binding.btnDel.setOnClickListener(view -> delClick());

        binding.btnZero.setOnClickListener(view -> setValue(binding.btnZero));
        binding.btnOne.setOnClickListener(view -> setValue(binding.btnOne));
        binding.btnTwo.setOnClickListener(view -> setValue(binding.btnTwo));
        binding.btnThree.setOnClickListener(view -> setValue(binding.btnThree));
        binding.btnFour.setOnClickListener(view -> setValue(binding.btnFour));
        binding.btnFive.setOnClickListener(view -> setValue(binding.btnFive));
        binding.btnSix.setOnClickListener(view -> setValue(binding.btnSix));
        binding.btnSeven.setOnClickListener(view -> setValue(binding.btnSeven));
        binding.btnEight.setOnClickListener(view -> setValue(binding.btnEight));
        binding.btnNine.setOnClickListener(view -> setValue(binding.btnNine));

        binding.btnDec.setOnClickListener(view -> setValue(binding.btnDec));
        binding.btnDivided.setOnClickListener(view -> setValue(binding.btnDivided));
        binding.btnPlus.setOnClickListener(view -> setValue(binding.btnPlus));
        binding.btnMin.setOnClickListener(view -> setValue(binding.btnMin));
        binding.btnMultiple.setOnClickListener(view -> setValue(binding.btnMultiple));

        binding.btnEquals.setOnClickListener(view -> equalsClick());

    }

    //Operators n Digits
    public void setValue(Button btn) {
        final String btnValue = btn.getText().toString().trim(); //fungsi trim = menghilangkan whitespace
        //final agar tdk bisa ngisi ulang value yg sdh diisi

        //kalau bkn operator, masuk d else
        if (isOperator(btnValue)) {
            if (selectedOperator.isEmpty()) {
                value = value + btnValue;
            } else {
                if (isOperator(value.substring(value.length() - 1))) {
                    final String oldValue = value.substring(0, value.length() - 1);

                    value = oldValue.concat(btnValue);
                }
            }
            selectedOperator = btnValue;
        } else {
            // jd digit, trs d cek lagi apakah 0 atau bkn. Kalau bkn nol maka ditimpa, kalau 0 ditambah
            if (Objects.equals(value, "0") ) {    //bandingkan
                if (btnValue.equals(".")) {
                    value = value + btnValue;
                }else {
                    value = btnValue;
                }
            } else {
                value = value + btnValue;
            }
        }

        binding.result.setText(value);
    }

    // fungsi untuk ngecek apakah btn merupakan operator
    public boolean isOperator(String value) {   //(parameter) bebas diisi apapun, yg penting string
        for (int i = 0; i < operators.length; i++) {
            if (value.equals(operators[i])) {
                return true;
            }
        }
        return false;

        //Cara Lain :
        //return Arrays.stream(operators).anyMatch(value::contains);
    }

    //All Clear
    public void clearClick() {
        value = "0";
        selectedOperator = "";
        binding.result.setText(value);
    }

    //Delete
    public void delClick() {
        if (isOperator(value.substring(value.length() - 1))) {
            selectedOperator = "";
        }

        if (value.length() > 1) {
            value = value.substring(0, value.length() - 1);
        } else {
            value = "0";
        }

        binding.result.setText(value);
    }

    //Equals
    public void equalsClick() {
        if (!selectedOperator.equals("") && value.length() > 1) {
            String[] splitValues = value.split(selectedOperator.equals("+") ? "\\+" : selectedOperator);

            double value1 = Double.parseDouble(splitValues[0]);
            double value2 = Double.parseDouble(splitValues[1]);

            double hasil = 0;

            //untuk ngecek selectedOperator
            switch (selectedOperator) {
                case "+":
                    hasil = value1 + value2;
                    break;
                case "-":
                    hasil = value1 - value2;
                    break;
                case "x":
                    hasil = value1 * value2;
                    break;
                case "/":
                    hasil = value1 / value2;
                    break;
            }

            // misal :
            // if 10 - 10.0 = 0
            // if 10 - 10.2 = -0.2
            if ((int) hasil - hasil == 0) {
                value = String.valueOf((int) hasil);
            } else {
                value = String.valueOf(hasil);
            }
            selectedOperator = "";

            binding.result.setText(value);
        }
    }
}