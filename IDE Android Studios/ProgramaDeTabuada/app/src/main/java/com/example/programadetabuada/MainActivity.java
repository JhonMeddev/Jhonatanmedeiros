package com.example.programadetabuada;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumero;
    private Button buttonCalcular;
    private TextView textViewTabuada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumero = findViewById(R.id.editTextNumero);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        textViewTabuada = findViewById(R.id.textViewResultado);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numero = Integer.parseInt(editTextNumero.getText().toString());
                String resultado = "";
                for (int i = 1; i <= 10; i++) {
                    resultado += numero + " x " + i + " = " + (numero * i) + "\n";
                }
                textViewTabuada.setText(resultado);
            }
        });
    }
}

