package com.example.pelculas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    private ListView listView;
    private AdaptadorPeliculas adapter;
    private List<Peliculas> listaPelis;
    private static final int REQUEST_CODE_ADD_MOVIE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ajusta el nombre de tu layout si es necesario

        // Inicializa el ListView
        listView = findViewById(R.id.listView);

        // Crea una lista de películas (puedes agregar más películas estáticas si lo deseas)
        listaPelis = new ArrayList<>();
        listaPelis.add(new Peliculas(R.drawable.it2, "IT", "Andrés Muschietti", "Varios niños de una pequeña ciudad del estado de Maine se alían para combatir a una entidad diabólica que adopta la forma de un payaso.", 3.0f));
        listaPelis.add(new Peliculas(R.drawable.loimposible, "Lo Imposible", "Juan Antonio Bayona", "Un tsunami destroza la costa del sudeste asiático, y una familia lucha por reencontrarse.", 4.0f));
        listaPelis.add(new Peliculas(R.drawable.ryan2, "Salvar al Soldado Ryan", "Steven Spielberg", "Soldados americanos arriesgan sus vidas para salvar al soldado James Ryan.", 4.5f));
        listaPelis.add(new Peliculas(R.drawable.resacon, "Resacón en Las Vegas", "Todd Phillips", "Cuatro amigos intentan resolver lo ocurrido en una alocada despedida de soltero.", 5.0f));
        listaPelis.add(new Peliculas(R.drawable.monstruo, "Un monstruo viene a verme", "Juan Antonio Bayona", "Un niño enfrenta sus miedos con la ayuda de un monstruo.", 4.0f));

        // Configura el adaptador para el ListView
        adapter = new AdaptadorPeliculas(this, listaPelis);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); // Ajusta si tienes un archivo XML para el menú
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opcion1) {
            // Ordenar por valoración descendente
            ordenarPorValoracionDesc();
            return true;
        } else if (id == R.id.opcion2) {
            // Ordenar por valoración ascendente
            ordenarPorValoracionAsc();
            return true;
        } else if (id == R.id.opcion3) {
            // Ordenar por director
            ordenarPorDirector();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Si se ha agregado una nueva película
        if (requestCode == REQUEST_CODE_ADD_MOVIE && resultCode == RESULT_OK) {
            // Recuperar la nueva película desde el Intent
            Peliculas nuevaPelicula = (Peliculas) data.getSerializableExtra("nuevaPelicula");

            if (nuevaPelicula != null) {
                // Mostrar el log para verificar si se recibe correctamente la película
                Log.d("MainActivity4", "Película recibida: " + nuevaPelicula.getNombre());

                // Agregar la nueva película a la lista
                listaPelis.add(nuevaPelicula);

                // Notificar al adaptador para que se actualice el ListView
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Elemento añadido con éxito.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Ordenar de mayor a menor valoración
    private void ordenarPorValoracionDesc() {
        listaPelis.sort((p1, p2) -> Float.compare(p2.getValoracion(), p1.getValoracion())); // Descendente
        adapter.notifyDataSetChanged();
    }

    // Ordenar de menor a mayor valoración
    private void ordenarPorValoracionAsc() {
        listaPelis.sort((p1, p2) -> Float.compare(p1.getValoracion(), p2.getValoracion())); // Ascendente
        adapter.notifyDataSetChanged();
    }

    // Ordenar por director
    private void ordenarPorDirector() {
        listaPelis.sort((p1, p2) -> p1.getDirector().compareToIgnoreCase(p2.getDirector())); // Orden alfabético por director
        adapter.notifyDataSetChanged();
    }
}
