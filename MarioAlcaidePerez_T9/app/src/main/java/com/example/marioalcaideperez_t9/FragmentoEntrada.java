package com.example.marioalcaideperez_t9;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentoEntrada extends Fragment {

    public interface Callbacks {
        void onTextoEnviado(String texto);
    }

    private Callbacks callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragmento_writer, container, false);

        EditText campoTexto = vista.findViewById(R.id.inputEditText);
        Button botonEnviar = vista.findViewById(R.id.sendButton);

        botonEnviar.setOnClickListener(v -> {
            String texto = campoTexto.getText().toString();
            if (!texto.isEmpty() && callback != null) {
                callback.onTextoEnviado(texto);
            }
        });

        return vista;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            callback = (Callbacks) context;
        } else {
            throw new ClassCastException(context.toString() + " debe implementar OnTextoEnviadoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
