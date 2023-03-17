package com.example.programabuscarcep;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText cepInput;
    private Button searchBtn;
    private TextView streetName, neighborhood, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cepInput = findViewById(R.id.cepInput);
        searchBtn = findViewById(R.id.searchBtn);
        streetName = findViewById(R.id.streetName);
        neighborhood = findViewById(R.id.neighborhood);
        city = findViewById(R.id.city);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = cepInput.getText().toString().trim();

                if (TextUtils.isEmpty(cep)) {
                    Toast.makeText(MainActivity.this, "Insira um CEP válido", Toast.LENGTH_SHORT).show();
                } else {
                    String url = "https://viacep.com.br/ws/" + cep + "/json/";

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String street = response.getString("logradouro");
                                        String neighborhoodName = response.getString("bairro");
                                        String cityName = response.getString("localidade");

                                        streetName.setText(street);
                                        neighborhood.setText(neighborhoodName);
                                        city.setText(cityName);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(MainActivity.this, "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Volley.newRequestQueue(MainActivity.this).add(request);
                }
            }
        });
    }
}