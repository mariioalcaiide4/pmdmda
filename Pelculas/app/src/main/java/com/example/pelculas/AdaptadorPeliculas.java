package com.example.pelculas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.PopupMenu;

import java.util.List;

public class AdaptadorPeliculas extends BaseAdapter {
    private Context context;
    private List<Peliculas> listaPelis;

    public AdaptadorPeliculas(Context context, List<Peliculas> listaPelis) {
        this.context = context;
        this.listaPelis = listaPelis;
    }

    @Override
    public int getCount() {
        return listaPelis.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPelis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_pelicula, null); // Asegúrate de que el nombre del layout es correcto
        }

        // Configura los elementos del ítem
        Peliculas pelicula = listaPelis.get(position);

        ImageView imagen = view.findViewById(R.id.imagen);
        TextView nombre = view.findViewById(R.id.nombrePelicula);
        TextView director = view.findViewById(R.id.directorPelicula);
        ImageView menuImageView = view.findViewById(R.id.menu_pelicula);
        TextView resumen = view.findViewById(R.id.breveResumenPeliculas);
        RatingBar valoracion = view.findViewById(R.id.valoracionPeliculas);


        // Asignar los valores
        imagen.setImageResource(pelicula.getImagenResId());
        nombre.setText(pelicula.getNombre());
        director.setText(pelicula.getDirector());
        resumen.setText(pelicula.getResumen());
        valoracion.setRating(pelicula.getValoracion());

        // Configura el evento de clic en los tres puntos
        menuImageView.setOnClickListener(v -> {
            // Crea el PopupMenu
            PopupMenu popupMenu = new PopupMenu(context, v);
            popupMenu.inflate(R.menu.menu_contextual); // Infla el menú contextual desde XML

            // Agrega las acciones para cada ítem del menú contextual
            popupMenu.setOnMenuItemClickListener(item -> {
                // Maneja las acciones del menú contextual aquí
                if (item.getItemId() == R.id.menu_añadir) {
                    // Acción para añadir una nueva película
                    Intent intentAñadir = new Intent(context, Formulario.class);
                    context.startActivity(intentAñadir);
                    return true;
                } else if (item.getItemId() == R.id.menu_editar) {
                    // Acción para editar la película seleccionada
                    Intent intentEditar = new Intent(context, Formulario.class);
                    intentEditar.putExtra("pelicula", pelicula); // Pasa el objeto Peliculas a la actividad Formulario
                    context.startActivity(intentEditar);
                    return true;
                } else if (item.getItemId() == R.id.menu_eliminar) {
                    // Acción para eliminar la película seleccionada
                    listaPelis.remove(position);
                    notifyDataSetChanged(); // Notifica al adaptador para actualizar la vista
                    return true;
                }
                return false;
            });

            // Muestra el menú
            popupMenu.show();
        });

        return view;
    }
}
