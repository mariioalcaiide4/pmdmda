package com.example.pelculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class Formulario extends AppCompatActivity {

    private EditText formNombre, formDirector, formResumen;
    private RatingBar formValoracion;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        formNombre = findViewById(R.id.formNombre);
        formDirector = findViewById(R.id.formDirector);
        formResumen = findViewById(R.id.formResumen);
        formValoracion = findViewById(R.id.formValoracion);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
            String nombre = formNombre.getText().toString().trim();
            String director = formDirector.getText().toString().trim();
            String resumen = formResumen.getText().toString().trim();
            float valoracion = formValoracion.getRating();

            int imagenPredeterminada = R.drawable.imagen_predeterminada;

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


