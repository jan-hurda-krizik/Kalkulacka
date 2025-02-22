package com.example.kalkulacka;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kalkulacka.R;

import java.math.BigInteger;

/**
 * Hlavní aktivita kalkulačky.
 * Tato třída obsahuje veškerou logiku pro zpracování vstupů, volbu operace a výpočet výsledku.
 */
public class MainActivity extends AppCompatActivity {

    // UI komponenty:
    // EditTexty pro zadání vstupních čísel.
    private EditText editTextNumber1, editTextNumber2;
    // Spinner pro výběr matematické operace.
    private Spinner spinnerOperation;
    // Tlačítko pro spuštění výpočtu.
    private Button buttonCalculate;
    // TextView pro zobrazení výsledku.
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Nastavení layoutu z activity_main.xml
        setContentView(R.layout.activity_main);

        // Inicializace UI komponent nalezením jejich ID definovaných v XML
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        spinnerOperation = findViewById(R.id.spinnerOperation);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Nastavení posluchače tlačítka, který při kliknutí zavolá metodu calculate()
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(); // Volání metody pro výpočet výsledku
            }
        });
    }

    /**
     * Metoda pro zpracování vstupů, ověření jejich správnosti a provedení vybrané matematické operace.
     */
    private void calculate() {
        // Získání vybrané operace ze Spinneru jako řetězce
        String operation = spinnerOperation.getSelectedItem().toString();
        // Načtení vstupních hodnot a odstranění přebytečných mezer
        String input1 = editTextNumber1.getText().toString().trim();
        String input2 = editTextNumber2.getText().toString().trim();

        // Kontrola, zda bylo zadáno první číslo
        if (input1.isEmpty()) {
            textViewResult.setText("Zadejte číslo 1");
            return;
        }

        try {
            // Převod prvního vstupu na číslo typu double
            double num1 = Double.parseDouble(input1);
            double num2 = 0;
            // U operace "Faktoriál" není potřeba druhé číslo
            if (!operation.equals("Faktoriál")) {
                if (input2.isEmpty()) {
                    textViewResult.setText("Zadejte číslo 2");
                    return;
                }
                // Převod druhého vstupu na číslo typu double
                num2 = Double.parseDouble(input2);
            }

            String resultText; // Proměnná pro uložení výsledku jako řetězce

            // Výběr operace pomocí switch-case
            switch (operation) {
                case "Sčítání":
                    resultText = String.valueOf(num1 + num2); // Výpočet součtu
                    break;
                case "Odčítání":
                    resultText = String.valueOf(num1 - num2); // Výpočet rozdílu
                    break;
                case "Násobení":
                    resultText = String.valueOf(num1 * num2); // Výpočet součinu
                    break;
                case "Dělení":
                    // Kontrola, zda nedochází k dělení nulou
                    if (num2 == 0) {
                        resultText = "Nelze dělit nulou";
                    } else {
                        resultText = String.valueOf(num1 / num2); // Výpočet podílu
                    }
                    break;
                case "Modulo":
                    // Kontrola dělení nulou u modulo operace
                    if (num2 == 0) {
                        resultText = "Nelze dělit nulou";
                    } else {
                        resultText = String.valueOf(num1 % num2); // Výpočet zbytku po dělení
                    }
                    break;
                case "Ntou mocninu":
                    resultText = String.valueOf(Math.pow(num1, num2)); // Výpočet num1 umocněného na num2
                    break;
                case "Ntou odmocninu":
                    // Kontrola, aby nedošlo k dělení nulou při výpočtu odmocniny
                    if (num2 == 0) {
                        resultText = "Nulá odmocnina není definována";
                    } else {
                        resultText = String.valueOf(Math.pow(num1, 1.0 / num2)); // Výpočet nté odmocniny
                    }
                    break;
                case "Faktoriál":
                    // Kontrola, zda je číslo celé a nezáporné
                    if (num1 < 0 || num1 != (int) num1) {
                        resultText = "Faktoriál je definován jen pro nezáporná celá čísla";
                    } else {
                        resultText = factorial((int) num1).toString(); // Výpočet faktoriálu pomocí rekurzivní metody
                    }
                    break;
                default:
                    resultText = "Neznámá operace"; // Případ, kdy operace není rozpoznána
                    break;
            }

            // Zobrazení výsledku ve TextView
            textViewResult.setText(resultText);
        } catch (NumberFormatException e) {
            // Zobrazení chybové zprávy při špatném formátu vstupu
            textViewResult.setText("Neplatný vstup");
        }
    }

    /**
     * Rekurzivní metoda pro výpočet faktoriálu čísla n.
     *
     * @param n číslo, pro které se počítá faktoriál (musí být nezáporné celé číslo)
     * @return faktoriál čísla n jako BigInteger
     */
    private BigInteger factorial(int n) {
        if (n == 0 || n == 1) {
            // Faktoriál 0 a 1 je roven 1
            return BigInteger.ONE;
        } else {
            // Rekurzivní volání: n * faktoriál(n-1)
            return BigInteger.valueOf(n).multiply(factorial(n - 1));
        }
    }
}
