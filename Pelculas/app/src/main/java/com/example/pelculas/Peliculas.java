package com.example.pelculas;

import java.io.Serializable;

public class Peliculas implements Serializable {

    int imagen;
    String nombre;
    String director;
    String resumen;
    float valoracion;

    //Constructor por defecto

    public Peliculas(int imagen, String nombre, String director, String resumen, float valoracion){

        this.imagen = imagen;
        this.nombre = nombre;
        this.director = director;
        this.resumen = resumen;
        this.valoracion = valoracion;
    }

    //Getters

    public int getImagenResId() { return imagen; }
    public String getNombre(){return nombre;}
    public String getDirector(){return director;}
    public String getResumen(){return resumen;}
    public float getValoracion(){return valoracion;}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Peliculas) {
            Peliculas other = (Peliculas) obj;
            return nombre.equals(other.nombre); // Comparar por nombre
        }
        return false;
    }

}
