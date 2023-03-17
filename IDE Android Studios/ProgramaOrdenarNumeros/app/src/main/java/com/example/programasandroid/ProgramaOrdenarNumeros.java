package com.example.programasandroid;

import static android.widget.Toast.makeText;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class ProgramaOrdenarNumeros extends AppCompatActivity {
    private EditText editText;
    private ArrayList<Integer> numbers;
    private ArrayAdapter<Integer> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programa_ordenar_numeros);

        editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);
        ListView listView = findViewById(R.id.listView);

        numbers = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String numberString = editText.getText().toString();
            if (!numberString.isEmpty()) {
                int number = Integer.parseInt(numberString);
                numbers.add(number);
                Collections.sort(numbers);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNumbersToFile();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    private void saveNumbersToFile() {
        try {
            // Cria um objeto JSON a partir dos números visualizados
            JSONArray numbersArray = new JSONArray(numbers);

            // Define o caminho e o nome do arquivo
            String fileName = "numbers.json";
            File dir = Environment.getExternalStorageDirectory();
            File file = new File(dir, fileName);

            // Grava o objeto JSON no arquivo
            FileWriter writer = new FileWriter(file);
            writer.write(numbersArray.toString());
            writer.flush();
            writer.close();

            // Exibe uma mensagem informando que o arquivo foi gravado com sucesso
            makeText(this, "", Toast.LENGTH_SHORT).show();makeText(this, "Números gravados em " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Exibe uma mensagem de erro em caso de falha na gravação
            makeText(this, "Erro ao gravar números em arquivo", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
