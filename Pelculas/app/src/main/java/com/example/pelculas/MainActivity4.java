package com.example.pelculas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    private ListView listView;
    private AdaptadorPeliculas adapter;
    private List<Peliculas> listaPelis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el ListView
        listView = findViewById(R.id.listView);

        // Configura el adaptador para el ListView
        adapter = new AdaptadorPeliculas(this, listaPelis);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opcion1) {
            ordenarPorValoracionDesc();
            return true;
        } else if (id == R.id.opcion2) {
            ordenarPorValoracionAsc();
            return true;
        } else if (id == R.id.opcion3) {
            ordenarPorDirector();
            return true;
        } else if (id == R.id.info) { // Nueva opción en el menú
            String contenidoInfo = leerArchivoTexto(R.raw.info);
            mostrarDialogoInfo(contenidoInfo);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    private void ordenarPorValoracionDesc() {
        listaPelis.sort((p1, p2) -> Float.compare(p2.getValoracion(), p1.getValoracion()));
        adapter.notifyDataSetChanged();
    }

    private void ordenarPorValoracionAsc() {
        listaPelis.sort((p1, p2) -> Float.compare(p1.getValoracion(), p2.getValoracion()));
        adapter.notifyDataSetChanged();
    }

    private void ordenarPorDirector() {
        listaPelis.sort((p1, p2) -> p1.getDirector().compareToIgnoreCase(p2.getDirector()));
        adapter.notifyDataSetChanged();
    }

    // Método para leer el archivo de texto desde res/raw
    private String leerArchivoTexto(int resId) {
        StringBuilder contenido = new StringBuilder();
        try (InputStream inputStream = getResources().openRawResource(resId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al leer el archivo.";
        }
        return contenido.toString();
    }

    private void mostrarDialogoInfo(String contenido) {
        new AlertDialog.Builder(this)
                .setTitle("Información de la App")
                .setMessage(contenido)
                .setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss())
                .show();
    }
}



