package com.example.prueba4_moviles;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private AdaptadorFamosos adapter;
    private List<Famosos> listaFamosos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializa el Spinner
        spinner = findViewById(R.id.spinner);

        // Crea la lista de elementos
        listaFamosos = new ArrayList<>();
        listaFamosos.add(new Famosos(R.drawable.caravaggio, "CARAVAGGIO", "Pintor italiano entre los años de 1593 y 1610. Es considerado como el primer gran exponente de la pintura del Barroco."));
        listaFamosos.add(new Famosos(R.drawable.rafael, "RAFAEL SANZIO", "Pintor y arquitecto italiano del Renacimiento, realizó importantes aportes en la arquitectura y, como inspector de antigüedades."));
        listaFamosos.add(new Famosos(R.drawable.velazquez, "VELAZQUEZ", "Pintor Barroco nacido en Sevilla en 1599, es considerado uno de los máximos exponentes de la pintura española y maestro de la pintura universal."));
        listaFamosos.add(new Famosos(R.drawable.miguelangel, "MIGUEL ANGEL", "Arquitecto, escultor y pintor italiano renacentista, considerado uno de los más grandes artistas de la historia."));
        listaFamosos.add(new Famosos(R.drawable.rembrant, "REMBRANDT", "Pintor y grabador holandés. La historia del arte le considera uno de los mayores maestros barrocos de la pintura y el grabado."));
        listaFamosos.add(new Famosos(R.drawable.boticelli, "BOTICELLI", "Apodado Sandro Botticelli, fue un pintor cuatrocentista italiano.su obra se ha considerado representativa de la pintura del primer Renacimiento."));
        listaFamosos.add(new Famosos(R.drawable.leonardo, "LEONARDO DA VINCI", "Notable polímata del Renacimiento italiano (a la vez anatomista, arquitecto, artista, botánico, científico, escritor, escultor, filósofo,ingeniero...)"));
        listaFamosos.add(new Famosos(R.drawable.renoir, "RENOIR", "Pintor francés impresionista, interesado por la pintura de cuerpos femeninos en paisajes, inspirados a menudo en pinturas clásicas renacentistas y barrocas."));


        // Agrega los demás elementos...

        // Configura el adaptador para el Spinner
        adapter = new AdaptadorFamosos(this, listaFamosos);
        spinner.setAdapter(adapter);

        // Configuración para el padding del sistema (opcional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}