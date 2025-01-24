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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    private ListView listView;
    private AdaptadorPeliculas adapter;
    private List<Peliculas> listaPelis;
    private DatabaseHelper dbHelper;
    private static final int REQUEST_CODE_ADD_MOVIE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el ListView
        listView = findViewById(R.id.listView);

        // Inicializa el DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Recuperar datos de la base de datos
        listaPelis = dbHelper.getAllPeliculas();

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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_MOVIE && resultCode == RESULT_OK) {
            Peliculas nuevaPelicula = (Peliculas) data.getSerializableExtra("nuevaPelicula");

            if (nuevaPelicula != null) {
                Log.d("MainActivity4", "Película recibida: " + nuevaPelicula.getNombre());

                // Añadir la película a la base de datos
                dbHelper.insertPelicula(nuevaPelicula);

                // Actualizar la lista desde la base de datos
                listaPelis.clear();
                listaPelis.addAll(dbHelper.getAllPeliculas());
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Elemento añadido con éxito.", Toast.LENGTH_SHORT).show();
            }
        }
    }

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

}



