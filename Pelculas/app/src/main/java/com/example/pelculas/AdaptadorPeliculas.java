package com.example.peliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pelculas.R;

import java.util.List;

public class AdaptadorPeliculas extends BaseAdapter {

    private final Context context;
    private final List<Peliculas> peliculas;

    // Constructor
    public AdaptadorPeliculas(Context context, List<Peliculas> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int position) {
        return peliculas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflar la vista solo si es necesario
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_pelicula, parent, false);
        }

        // Obtener la película actual
        Peliculas pelicula = peliculas.get(position);

        // Configurar las vistas del layout
        ImageView imagen = convertView.findViewById(R.id.imagen);
        TextView nombre = convertView.findViewById(R.id.nombrePelicula);
        TextView director = convertView.findViewById(R.id.directorPelicula);
        TextView resumen = convertView.findViewById(R.id.breveResumenPeliculas);
        RatingBar valoracion = convertView.findViewById(R.id.valoracionPeliculas);

        // Asignar los valores
        imagen.setImageResource(pelicula.getImagenResId());
        nombre.setText(pelicula.getNombre());
        director.setText(pelicula.getDirector());
        resumen.setText(pelicula.getResumen());
        valoracion.setRating(pelicula.getValoracion());

        return convertView;
    }
}
