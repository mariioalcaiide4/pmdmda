package com.example.propuesta_94;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido {
    public static class Lista_entrada {

        public static ArrayList<Lista_entrada> ENT_LISTA = new ArrayList<Lista_entrada>();

        public static Map<String, Lista_entrada> ENT_LISTA_HASHMAP = new HashMap<String, Lista_entrada>();

        public String id, textoEncima, textoDebajo;
        public int idImagen;


        public Lista_entrada(String id) {
            this.id = id;
        }

        private static void ponerEntrada(Lista_entrada entrada){
            ENT_LISTA.add(entrada);
            ENT_LISTA_HASHMAP.put(entrada.id, entrada);
        }



    }
}

