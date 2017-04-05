package com.domini;

import java.util.ArrayList;

/**
 * Clase que guarda preguntas cualitativas no ordenadas, con una sola opcion a escoger.
 */
public class PregCualitativaNoOrdenadaUnica extends Pregunta {
    private ArrayList<String> opciones;

    /**
     * Constructora de la clase.
     * @param opciones El conjunto de opciones de la encuesta.
     */
    public PregCualitativaNoOrdenadaUnica(String titulo, ArrayList<String> opciones) {
        super(titulo);
        this.opciones = opciones;
    }

    public void PosiblesRespuestas(){
        System.out.println("Elige una de estas opciones");
        for (int i : opciones) {
            System.out.println(i + ": " + opciones[i]);
        }
    }
}
