package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kalkulacka.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializace komponent z layoutu
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Listener pro tlačítko - provede sčítání
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAddition();
            }
        });
    }

    // Metoda provede sčítání dvou zadaných čísel
    private void calculateAddition() {
        String input1 = editTextNumber1.getText().toString().trim();
        String input2 = editTextNumber2.getText().toString().trim();

        if (input1.isEmpty() || input2.isEmpty()) {
            textViewResult.setText("Zadejte obě čísla");
            return;
        }

        try {
            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);
            double sum = num1 + num2;
            textViewResult.setText(String.valueOf(sum));
        } catch (NumberFormatException e) {
            textViewResult.setText("Neplatný vstup");
        }
    }
}
