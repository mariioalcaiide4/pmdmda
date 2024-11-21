package com.example.peliculas;

import android.os.Bundle;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pelculas.R;

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
        ListView listView = findViewById(R.id.listView);

        // Crea una lista de elementos (películas)
        listaPelis = new ArrayList<>();
        listaPelis.add(new Peliculas(R.drawable.it, "IT", "Andrés Muschietti", "Varios niños de una pequeña ciudad del estado de Maine se alían para combatir a una entidad diabólica que adopta la forma de un payaso y emerge cada 27 años para saciarse de sangre infantil.", 3.0f));
        listaPelis.add(new Peliculas(R.drawable.loimposible, "Lo Imposible", "Juan Antonio Bayona", "Un tsunami destroza la costa del sudeste asiático, y una familia lucha por reencontrarse.", 4.0f));
        listaPelis.add(new Peliculas(R.drawable.ryan, "Salvar al Soldado Ryan", "Steven Spielberg", "Soldados americanos arriesgan sus vidas para salvar al soldado James Ryan.", 4.5f));
        listaPelis.add(new Peliculas(R.drawable.resacon, "Resacón en Las Vegas", "Todd Phillips", "Cuatro amigos intentan resolver lo ocurrido en una alocada despedida de soltero.", 5.0f));
        listaPelis.add(new Peliculas(R.drawable.monstruo, "Un monstruo viene a verme", "Juan Antonio Bayona", "Un niño enfrenta sus miedos con la ayuda de un monstruo.", 4.0f));

        // Configura el adaptador para el ListView
        AdaptadorPeliculas adapter = new AdaptadorPeliculas(this, listaPelis);
        listView.setAdapter(adapter);

        // Configuración para el padding del sistema (opcional, si se necesita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}


