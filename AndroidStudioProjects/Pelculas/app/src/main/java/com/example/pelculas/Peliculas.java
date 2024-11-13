package com.example.pelculas;

public class Peliculas {

    String titulo;
    String director;
    String genero;
    int premios;
    float calificacion;

    //Constructor por defecto

    public Peliculas(String titulo, String director, String genero, int premios, float calificacion){

        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.premios = premios;
        this.calificacion = calificacion;
    }

    //Getters

    public String getTitulo(){return titulo;}
    public String getDirector(){return director;}
    public String getGenero(){return genero;}
    public int getPremios(){return premios;}
    public float getCalificacion(){return calificacion;}

}
