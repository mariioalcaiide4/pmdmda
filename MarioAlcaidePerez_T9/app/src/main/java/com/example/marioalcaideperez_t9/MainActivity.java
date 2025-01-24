package com.example.marioalcaideperez_t9;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragmentoEntrada.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragmentoEntrada, new FragmentoEntrada())
                .replace(R.id.contenedorFragmentoSalida, new FragmentoSalida())
                .commit();
    }

    @Override
    public void onTextoEnviado(String texto) {
        FragmentoSalida fragmentoMostrar = (FragmentoSalida)
                getSupportFragmentManager().findFragmentById(R.id.contenedorFragmentoSalida);

        if (fragmentoMostrar != null) {
            fragmentoMostrar.actualizarTexto(texto);
        }
    }
}
