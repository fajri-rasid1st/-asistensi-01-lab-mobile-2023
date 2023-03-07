package com.muammarahlnn.activityassignment2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.muammarahlnn.activityassignment2.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Geometries selectedGeometry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.spinnerGeometries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setCurrentSelectedGeometry();
                arrangeCalculationInputs();
                binding.tvResult.setText("Hasil");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // no op
            }
        });
        binding.btnCalculate.setOnClickListener(view -> performCalculation());
    }

    private void setCurrentSelectedGeometry() {
        int ordinal = binding.spinnerGeometries.getSelectedItemPosition();
        switch (ordinal) {
            case 0 -> selectedGeometry = Geometries.BOLA;
            case 1 -> selectedGeometry = Geometries.KERUCUT;
            case 2 -> selectedGeometry = Geometries.BALOK;
            default -> throw new IllegalStateException("Unexpected value: " + selectedGeometry);
        }
    }

    private void arrangeCalculationInputs() {
        switch (selectedGeometry) {
            case BOLA -> showBolaInputs();
            case KERUCUT -> showKerucutInputs();
            case BALOK -> showBalokInputs();
            default -> throw new IllegalStateException("Unexpected value: " + selectedGeometry);
        }
    }

    private void showBolaInputs() {
        // only shows first input and change its field's name
        showSecondInput(false);
        showThirdInput(false);
        binding.tvFirstInput.setText("Jari - jari");
    }

    private void showKerucutInputs() {
        // only shows first and second input and change its field's name
        showSecondInput(true);
        showThirdInput(false);
        binding.tvFirstInput.setText("Jari - jari");
        binding.tvSecondInput.setText("Tinggi");
    }

    private void showBalokInputs() {
        // shows all inputs and change its field's name
        showSecondInput(true);
        showThirdInput(true);
        binding.tvFirstInput.setText("Panjang");
        binding.tvSecondInput.setText("Lebar");
        binding.tvThirdInput.setText("Tinggi");
    }

    private void showSecondInput(boolean isShow) {
        if (isShow) {
            binding.tvSecondInput.setVisibility(View.VISIBLE);
            binding.etSecondInput.setVisibility(View.VISIBLE);
        } else {
            binding.tvSecondInput.setVisibility(View.GONE);
            binding.etSecondInput.setVisibility(View.GONE);
        }
    }

    private void showThirdInput(boolean isShow) {
        if (isShow) {
            binding.tvThirdInput.setVisibility(View.VISIBLE);
            binding.etThirdInput.setVisibility(View.VISIBLE);
        } else {
            binding.tvThirdInput.setVisibility(View.GONE);
            binding.etThirdInput.setVisibility(View.GONE);
        }
    }

    private void performCalculation() {
        if (!isAllInputsValid()) return;
        switch (selectedGeometry) {
            case BOLA -> calculateBola();
            case KERUCUT -> calculateKerucut();
            case BALOK -> calculateBalok();
            default -> throw new IllegalStateException("Unexpected value: " + selectedGeometry);
        }
    }

    private boolean isAllInputsValid() {
        switch (selectedGeometry) {
            case BOLA ->  {
                return checkInput(binding.etFirstInput);
            }
            case KERUCUT -> {
                return checkInput(
                    binding.etFirstInput,
                    binding.etSecondInput
                );
            }
            case BALOK -> {
                return checkInput(
                    binding.etFirstInput,
                    binding.etSecondInput,
                    binding.etThirdInput
                );
            }
            default -> throw new IllegalStateException("Unexpected value: " + selectedGeometry);
        }
    }

    private boolean checkInput(EditText... editTexts) {
        boolean isInputValid = true;
        for (EditText editText : editTexts) {
            String input = getEditTextValue(editText);
            if (input.isEmpty()) {
                editText.setError("Field ini tidak boleh kosong");
                isInputValid = false;
            }
        }
        return isInputValid;
    }

    private String getEditTextValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void calculateBola() {
        try {
            double radius = Double.parseDouble(getEditTextValue(binding.etFirstInput));
            double result = (double) 4 / 3 * Math.PI * Math.pow(radius, 3);
            setResult(result);
        } catch (NumberFormatException ex) {
            showErrorNumberToast();
            Log.d("MainActivity", ex.getLocalizedMessage());
        }
    }

    private void calculateKerucut() {
        try {
            double radius = Double.parseDouble(getEditTextValue(binding.etFirstInput));
            double height = Double.parseDouble(getEditTextValue(binding.etSecondInput));
            double result = (double) 1 / 3 * Math.PI * Math.pow(radius, 2) * height;
            setResult(result);
        } catch (NumberFormatException ex) {
            showErrorNumberToast();
            Log.d("MainActivity", ex.getLocalizedMessage());
        }
    }

    private void calculateBalok() {
        try {
            double length = Double.parseDouble(getEditTextValue(binding.etFirstInput));
            double width = Double.parseDouble(getEditTextValue(binding.etSecondInput));
            double height = Double.parseDouble(getEditTextValue(binding.etThirdInput));
            double result = length * width * height;
            setResult(result);
        } catch (NumberFormatException ex) {
            showErrorNumberToast();
            Log.d("MainActivity", ex.getLocalizedMessage());
        }
    }

    private void setResult(double result) {
        String formattedResult = String.format(Locale.getDefault(),"%.2f", result);
        binding.tvResult.setText(formattedResult);
    }

    private void showErrorNumberToast() {
        Toast.makeText(this, "Can't calculate number more than 64 bit", Toast.LENGTH_SHORT).show();
    }
}