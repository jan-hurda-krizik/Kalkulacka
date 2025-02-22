package com.example.kalkulacka;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.math.BigInteger;

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

        // Listener tlačítka, který spustí výpočet
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    // Metoda provede výpočet podle vybrané operace
    private void calculate() {
        String operation = spinnerOperation.getSelectedItem().toString();
        String input1 = editTextNumber1.getText().toString().trim();
        String input2 = editTextNumber2.getText().toString().trim();

        // Ověření, že je zadáno číslo 1
        if (input1.isEmpty()) {
            textViewResult.setText("Zadejte číslo 1");
            return;
        }

        try {
            double num1 = Double.parseDouble(input1);
            double num2 = 0;
            // U operace "Faktoriál" se číslo 2 nevyužívá
            if (!operation.equals("Faktoriál")) {
                if (input2.isEmpty()) {
                    textViewResult.setText("Zadejte číslo 2");
                    return;
                }
                num2 = Double.parseDouble(input2);
            }

            String resultText;

            switch (operation) {
                case "Sčítání":
                    resultText = String.valueOf(num1 + num2);
                    break;
                case "Odčítání":
                    resultText = String.valueOf(num1 - num2);
                    break;
                case "Násobení":
                    resultText = String.valueOf(num1 * num2);
                    break;
                case "Dělení":
                    if (num2 == 0) {
                        resultText = "Nelze dělit nulou";
                    } else {
                        resultText = String.valueOf(num1 / num2);
                    }
                    break;
                case "Modulo":
                    if (num2 == 0) {
                        resultText = "Nelze dělit nulou";
                    } else {
                        resultText = String.valueOf(num1 % num2);
                    }
                    break;
                case "Ntou mocninu":
                    resultText = String.valueOf(Math.pow(num1, num2));
                    break;
                case "Ntou odmocninu":
                    if (num2 == 0) {
                        resultText = "Nulá odmocnina není definována";
                    } else {
                        resultText = String.valueOf(Math.pow(num1, 1.0 / num2));
                    }
                    break;
                case "Faktoriál":
                    // Kontrola, zda je číslo nezáporné a celé
                    if (num1 < 0 || num1 != (int) num1) {
                        resultText = "Faktoriál je definován jen pro nezáporná celá čísla";
                    } else {
                        resultText = factorial((int) num1).toString();
                    }
                    break;
                default:
                    resultText = "Neznámá operace";
                    break;
            }

            textViewResult.setText(resultText);
        } catch (NumberFormatException e) {
            textViewResult.setText("Neplatný vstup");
        }
    }

    // Rekurzivní metoda pro výpočet faktoriálu
    private BigInteger factorial(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(n).multiply(factorial(n - 1));
        }
    }
}
