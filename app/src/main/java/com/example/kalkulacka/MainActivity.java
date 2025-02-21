package com.example.kalkulacka;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kalkulacka.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Spinner spinnerOperation;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializace komponent z layoutu
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        spinnerOperation = findViewById(R.id.spinnerOperation);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Nastavení listeneru tlačítka pro výpočet
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    // Metoda pro provedení výpočtu dle zvolené operace
    private void calculate() {
        String operation = spinnerOperation.getSelectedItem().toString();
        String input1 = editTextNumber1.getText().toString().trim();
        String input2 = editTextNumber2.getText().toString().trim();

        if (input1.isEmpty() || input2.isEmpty()) {
            textViewResult.setText("Zadejte obě čísla");
            return;
        }

        try {
            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);
            double result = 0;

            if (operation.equals("Sčítání")) {
                result = num1 + num2;
            } else if (operation.equals("Odčítání")) {
                result = num1 - num2;
            }

            textViewResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textViewResult.setText("Neplatný vstup");
        }
    }
}
