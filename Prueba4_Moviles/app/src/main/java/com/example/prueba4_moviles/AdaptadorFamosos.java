package com.example.prueba4_moviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorFamosos extends ArrayAdapter<Famosos> {
    private final Context context;
    private final List<Famosos> listaFamosos;

    public AdaptadorFamosos(Context context, List<Famosos> listaFamosos) {
        super(context, R.layout.item_famoso, listaFamosos);
        this.context = context;
        this.listaFamosos = listaFamosos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromPosition(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createViewFromPosition(position, convertView, parent);
    }

    private View createViewFromPosition(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_famoso, parent, false);
        }


        Famosos famoso = listaFamosos.get(position);

        TextView nombre = convertView.findViewById(R.id.nombreFamoso);
        nombre.setText(famoso.getNombre());

        TextView descripcion = convertView.findViewById(R.id.descripcionFamoso);
        descripcion.setText(famoso.getNombre());

        ImageView imagen = convertView.findViewById(R.id.imagen);
        imagen.setImageResource(famoso.getImagenResId());


        // Configura el WebView
        WebView webView = convertView.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilita JavaScript si es necesario
        webView.loadData(famoso.getDescripcion(), "text/html", "UTF-8");

        return convertView;
    }
}
