package com.example.videogamee;

import java.io.Serializable;

public class Juegos implements Serializable {

    int portada;
    String titulo;
    String plataforma;
    String estudio;
    String genero;
    float valoracion;


    public Juegos(int portada, String titulo, String plataforma, String estudio, String genero, float valoracion){

        this.portada = portada;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.estudio = estudio;
        this.genero = genero;
        this.valoracion = valoracion;

    }

    //Getters

    public int getPortada() {return portada;}
    public String getTitulo() {return titulo;}
    public String getPlataforma() {return plataforma;}
    public String getEstudio() {return estudio;}
    public String getGenero() {return genero;}
    public float getValoracion(){return valoracion;}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Juegos) {
            Juegos other = (Juegos) obj;
            return titulo.equals(other.titulo); // Comparar por nombre
        }
        return false;
    }
}
