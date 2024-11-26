package com.example.prueba4_moviles;

public class Famosos {

    int imagen;
    String nombre;
    String descripcion;

    //Constructor por defecto

    public Famosos(int imagen, String nombre, String descripcion){

        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Getters

    public int getImagenResId() { return imagen; }
    public String getNombre(){return nombre;}
    public String getDescripcion(){return descripcion;}

}
