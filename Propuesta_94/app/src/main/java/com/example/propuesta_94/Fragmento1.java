package com.example.propuesta_94;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

public class Fragmento1 extends ListFragment {
    private Callbacks mCallbacks = Callbacks.CallbacksVacios;

    public Fragmento1() {
        // Constructor vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ENT_LISTA) {
            @Override
            public void onEntrada(Object entrada, View view) {
                // Asignación de datos a la vista
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textoTitulo);
                texto_superior_entrada.setText(((Contenido.Lista_entrada) entrada).textoEncima);

                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagenLista);
                imagen_entrada.setImageResource(((Contenido.Lista_entrada) entrada).idImagen);
            }
        });

    }


    public void onAttach(@NonNull Activity activity) {
         super.onAttach(activity);
         mCallbacks = (Callbacks) activity;
    }

    public void onDetach() {
        super.onDetach();
        mCallbacks = Callbacks.CallbacksVacios;
    }


    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallbacks.onEntradaSeleccionada(Contenido.ENT_LISTA.get(position).id);

}

    public interface Callbacks {
        @Override
        public void onEntradaSeleccionada(String id);

        static Callbacks CallbacksVacios = new Callbacks() {
            @Override
            public void onEntradaSeleccionada(String id) {}
        };

    }

    mCallbacks.onEntradaSeleccionada(Contenido.ENT_LISTA.get(position).id);




}


