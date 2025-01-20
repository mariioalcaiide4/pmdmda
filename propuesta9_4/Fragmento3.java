package com.example.propuesta9_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragmento3 extends Fragment {
    public static final String ARG_ID_ENTRADA_SELECCIONADA = "item_id";
    private Contenido.Lista_entrada miItem;

    public Fragmento3() {
        // Constructor vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ID_ENTRADA_SELECCIONADA)) {
            miItem = Contenido.ENT_LISTA_HASHMAP.get(getArguments().getString(ARG_ID_ENTRADA_SELECCIONADA));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_detalle, container, false);
        if (miItem != null) {
            ((TextView) rootView.findViewById(R.id.textotitulo)).setText(miItem.textoEncima);
            ((TextView) rootView.findViewById(R.id.textocontenido)).setText(miItem.textoDebajo);
            ((ImageView) rootView.findViewById(R.id.imagen)).setImageResource(miItem.idImagen);
        }
        return rootView;
    }
}
