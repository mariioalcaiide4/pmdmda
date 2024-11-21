package com.example.pelculas;

public class Peliculas {

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

}
