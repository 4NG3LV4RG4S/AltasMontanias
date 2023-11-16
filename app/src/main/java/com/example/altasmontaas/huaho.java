package com.example.altasmontaas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class huaho extends AppCompatActivity {
    int contador = 0;
    Button b1, b2;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huaho);
        b1 = findViewById(R.id.btn_regresar);
        b2 = findViewById(R.id.btn_reserve);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla = new Intent(getApplicationContext(), Huatusco.class);
                startActivity(pantalla);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
                String mensaje = "El botón ha sido presionado " + contador + " veces";
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

    }
}