package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerFromUnit, spinnerToUnit;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextValue = findViewById(R.id.editTextValue);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        // Set up spinner adapters
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromUnit.setAdapter(adapter);
        spinnerToUnit.setAdapter(adapter);

        // Set button click listener
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String fromUnit = spinnerFromUnit.getSelectedItem().toString();
        String toUnit = spinnerToUnit.getSelectedItem().toString();
        String inputValueStr = editTextValue.getText().toString().trim();

        if (inputValueStr.isEmpty()) {
            textViewResult.setText("Please enter a valid number.");
            return;
        }

        try {
            double inputValue = Double.parseDouble(inputValueStr);
            double result = 0;

            if (fromUnit.equals("Centimeters") && toUnit.equals("Meters")) {
                result = inputValue / 100.0;  // Convert centimeters to meters
            } else if (fromUnit.equals("Meters") && toUnit.equals("Centimeters")) {
                result = inputValue * 100.0;  // Convert meters to centimeters
            } else if (fromUnit.equals("Grams") && toUnit.equals("Kilograms")) {
                result = inputValue / 1000.0; // Convert grams to kilograms
            } else if (fromUnit.equals("Kilograms") && toUnit.equals("Grams")) {
                result = inputValue * 1000.0; // Convert kilograms to grams
            } else {
                textViewResult.setText("Conversion not supported.");
                return;
            }

            textViewResult.setText(String.format("%.2f %s = %.2f %s", inputValue, fromUnit, result, toUnit));

        } catch (NumberFormatException e) {
            textViewResult.setText("Invalid input. Please enter a valid number.");
        }
    }
}
