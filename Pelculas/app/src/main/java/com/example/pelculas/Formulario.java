package com.example.pelculas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class Formulario extends AppCompatActivity {
    private EditText formNombre, formDirector, formResumen;
    private RatingBar formValoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        formNombre = findViewById(R.id.formNombre);
        formDirector = findViewById(R.id.formDirector);
        formResumen = findViewById(R.id.formResumen);
        formValoracion = findViewById(R.id.formValoracion);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            // Obtener los datos del formulario
            String nombre = formNombre.getText().toString().trim();
            String director = formDirector.getText().toString().trim();
            String resumen = formResumen.getText().toString().trim();
            float valoracion = formValoracion.getRating();

            int imagenPredeterminada = R.drawable.imagen_predeterminada;

            if (!nombre.isEmpty() && !director.isEmpty() && !resumen.isEmpty()) {
                // Crear el objeto Peliculas con los datos del formulario
                Peliculas nuevaPelicula = new Peliculas(imagenPredeterminada, nombre, director, resumen, valoracion);

                // Enviar la nueva película a MainActivity4
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nuevaPelicula", nuevaPelicula);
                setResult(RESULT_OK, resultIntent);

                // Finalizar la actividad
                finish();
            }
        });
    }
}


