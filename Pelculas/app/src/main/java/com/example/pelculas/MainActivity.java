package com.example.pelculas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private AdaptadorPeliculas adapter;
    private List<Peliculas> listaPelis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Configura el modo Edge-to-Edge
        setContentView(R.layout.activity_main); // Layout principal (asegúrate que este archivo exista y sea el correcto)

        // Inicializa el ListView
        listView = findViewById(R.id.listView);

        // Crea una lista de elementos (películas)
        listaPelis = new ArrayList<>();
        listaPelis.add(new Peliculas(R.drawable.it2, "IT", "Andrés Muschietti", "Varios niños de una pequeña ciudad del estado de Maine se alían para combatir a una entidad diabólica que adopta la forma de un payaso.", 3.0f));
        listaPelis.add(new Peliculas(R.drawable.loimposible, "Lo Imposible", "Juan Antonio Bayona", "Un tsunami destroza la costa del sudeste asiático, y una familia lucha por reencontrarse.", 4.0f));
        listaPelis.add(new Peliculas(R.drawable.ryan2, "Salvar al Soldado Ryan", "Steven Spielberg", "Soldados americanos arriesgan sus vidas para salvar al soldado James Ryan.", 4.5f));
        listaPelis.add(new Peliculas(R.drawable.resacon, "Resacón en Las Vegas", "Todd Phillips", "Cuatro amigos intentan resolver lo ocurrido en una alocada despedida de soltero.", 5.0f));
        listaPelis.add(new Peliculas(R.drawable.monstruo, "Un monstruo viene a verme", "Juan Antonio Bayona", "Un niño enfrenta sus miedos con la ayuda de un monstruo.", 4.0f));

        // Configura el adaptador para el ListView
        adapter = new AdaptadorPeliculas(this, listaPelis);
        listView.setAdapter(adapter);

        // Configuración para el padding del sistema (opcional, si se necesita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú desde el archivo XML
        getMenuInflater().inflate(R.menu.menu, menu); // Asegúrate de que el archivo XML se llame 'menu.xml'
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
            } else {
        return super.onOptionsItemSelected(item);
            }
    }

    // Ordenar de mayor a menor valoración
    private void ordenarPorValoracionDesc() {
        listaPelis.sort((p1, p2) -> Float.compare(p2.getValoracion(), p1.getValoracion())); // Descendente
        adapter.notifyDataSetChanged(); // Actualiza el adaptador
    }

    // Ordenar de menor a mayor valoración
    private void ordenarPorValoracionAsc() {
        listaPelis.sort((p1, p2) -> Float.compare(p1.getValoracion(), p2.getValoracion())); // Ascendente
        adapter.notifyDataSetChanged(); // Actualiza el adaptador
    }

    // Ordenar por director (alfabéticamente)
    private void ordenarPorDirector() {
        listaPelis.sort((p1, p2) -> p1.getDirector().compareToIgnoreCase(p2.getDirector())); // Orden alfabético por director
        adapter.notifyDataSetChanged(); // Actualiza el adaptador
    }

    private void ordenarPorLetra(){
        listaPelis.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        adapter.notifyDataSetChanged();
    }

    private void añadirPelicula(){
        Log.i("Añadir", "Crear película");
        Intent ventanaAñadir = new Intent(this, MainActivity3.class);
        startActivity(ventanaAñadir);
    }

}



