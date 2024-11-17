package com.project.spguia2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etDatos;
    Button btnIngresar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre=findViewById(R.id.etNombre);
        etDatos=findViewById(R.id.etDatos);
    }

    public void guardar(View view){
        String nombre=etNombre.getText().toString();
        String datos=etDatos.getText().toString();

        SharedPreferences sp=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
        editor.putString(nombre, datos);
        editor.commit();
        Toast.makeText(this,"Datos guardados",Toast.LENGTH_LONG).show();
    }

    public void recuperar(View view){
        String nombre=etNombre.getText().toString();

        SharedPreferences sp=getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String d=sp.getString(nombre,"");
        if (d.isEmpty()){
            Toast.makeText(this,"No existe ese nombre en la base de datos",Toast.LENGTH_LONG).show();
        }else {
            etDatos.setText(d);
        }
    }
}