package com.example.propuesta9_4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.FragmentActivity;

public class Actividad extends FragmentActivity implements Fragmento1.Callbacks {
private boolean dosFragmentos;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.frame_contenedor) != null){
            dosFragmentos=true;
        }


    }
    @Override
    public void onEntradaSeleccionada(String id) {
        if (dosFragmentos){
            Bundle arguments = new Bundle();
            arguments.putString(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
            Fragmento3 fragment = new Fragmento3();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_contenedor, fragment)
                    .commit();
        } else {
            Toast.makeText(getBaseContext(), "TOCADO EL "+id,Toast.LENGTH_SHORT).show();
            Intent detallelIntent = new Intent(this, Fragmento2.class);
            detallelIntent.putExtra(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
            startActivity(detallelIntent);


        }


    }
}