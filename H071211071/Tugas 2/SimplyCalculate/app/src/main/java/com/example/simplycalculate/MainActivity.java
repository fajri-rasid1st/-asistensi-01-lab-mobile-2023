package com.example.simplycalculate;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText etResult;
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven,
            btnEight, btnNine, btnZero, btnPoint, btnAdd, btnMinus, btnTimes, btnDivide, btnEqual, btnClear;

    double num1 = 0;
    int indicator = 0;

    ModelActivity c_model;
    ControllerActivity c_controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeVars();
    }

    public void initializeVars(){
        etResult = (EditText)findViewById(R.id.etResult);
        btnOne = (Button)findViewById(R.id.btnOne);
        btnTwo = (Button)findViewById(R.id.btnTwo);
        btnThree = (Button)findViewById(R.id.btnThree);
        btnFour = (Button)findViewById(R.id.btnFour);
        btnFive = (Button)findViewById(R.id.btnFive);
        btnSix = (Button)findViewById(R.id.btnSix);
        btnSeven = (Button)findViewById(R.id.btnSeven);
        btnEight = (Button)findViewById(R.id.btnEight);
        btnNine = (Button)findViewById(R.id.btnNine);
        btnZero = (Button)findViewById(R.id.btnZero);
        btnPoint = (Button)findViewById(R.id.btnPoint);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnTimes = (Button)findViewById(R.id.btnTimes);
        btnDivide = (Button)findViewById(R.id.btnDivide);
        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnClear = (Button)findViewById(R.id.btnClear);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnTimes.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        try{
            switch(v.getId()){
                case R.id.btnOne:
                    etResult.setText(etResult.getText().toString()+btnOne.getText().toString());
                    break;
                case R.id.btnTwo:
                    etResult.setText(etResult.getText().toString()+btnTwo.getText().toString());
                    break;
                case R.id.btnThree:
                    etResult.setText(etResult.getText().toString()+btnThree.getText().toString());
                    break;
                case R.id.btnFour:
                    etResult.setText(etResult.getText().toString()+btnFour.getText().toString());
                    break;
                case R.id.btnFive:
                    etResult.setText(etResult.getText().toString()+btnFive.getText().toString());
                    break;
                case R.id.btnSix:
                    etResult.setText(etResult.getText().toString()+btnSix.getText().toString());
                    break;
                case R.id.btnSeven:
                    etResult.setText(etResult.getText().toString()+btnSeven.getText().toString());
                    break;
                case R.id.btnEight:
                    etResult.setText(etResult.getText().toString()+btnEight.getText().toString());
                    break;
                case R.id.btnNine:
                    etResult.setText(etResult.getText().toString()+btnNine.getText().toString());
                    break;
                case R.id.btnZero:
                    etResult.setText(etResult.getText().toString()+btnZero.getText().toString());
                    break;

                case R.id.btnPoint:
                    etResult.setText(etResult.getText().toString()+btnPoint.getText().toString());
                    break;

                case R.id.btnAdd:
                    num1 = Double.parseDouble(etResult.getText().toString());
                    etResult.setText("");
                    indicator = 1;
                    break;

                case R.id.btnMinus:
                    num1 = Double.parseDouble(etResult.getText().toString());
                    etResult.setText("");
                    indicator = 2;
                    break;

                case R.id.btnTimes:
                    num1 = Double.parseDouble(etResult.getText().toString());
                    etResult.setText("");
                    indicator = 3;
                    break;

                case R.id.btnDivide:
                    num1 = Double.parseDouble(etResult.getText().toString());
                    etResult.setText("");
                    indicator = 4;
                    break;

                case R.id.btnClear:
                    etResult.setText("");
                    break;

                case R.id.btnEqual:

                    c_model = new ModelActivity(num1, Double.parseDouble(etResult.getText().toString()));
                    c_controller = new ControllerActivity();

                    switch(indicator){
                        case 1:
                            etResult.setText(Double.toString(c_controller.addition(c_model)));
                            break;
                        case 2:
                            etResult.setText(Double.toString(c_controller.subtraction(c_model)));
                            break;
                        case 3:
                            etResult.setText(Double.toString(c_controller.multiplication(c_model)));
                            break;
                        case 4:
                            etResult.setText(Double.toString(c_controller.divistion(c_model)));
                            break;
                    }

                    break;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}