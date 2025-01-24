package com.example.marioalcaideperez_t9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class FragmentoSalida extends Fragment {

    private TextView textoMostrado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragmento_con_display, container, false);
        textoMostrado = vista.findViewById(R.id.displayText);
        return vista;
    }

    public void actualizarTexto(String texto) {
        if (textoMostrado != null) {
            textoMostrado.setText(texto);
        }
    }
}

