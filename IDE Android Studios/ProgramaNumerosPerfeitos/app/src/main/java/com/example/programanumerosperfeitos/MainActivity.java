package com.example.programanumerosperfeitos;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberString = editText.getText().toString();
                if (!TextUtils.isEmpty(numberString)) {
                    int number = Integer.parseInt(numberString);
                    boolean isPerfect = isPerfectNumber(number);
                    if (isPerfect) {
                        Toast.makeText(MainActivity.this, number + " é um número perfeito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, number + " não é um número perfeito", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Digite um número", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isPerfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}
