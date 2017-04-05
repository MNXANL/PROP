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
    public PregCualitativaNoOrdenadaUnica(string titulo, ArrayList<String> opciones) {
        super(titulo);
        this.opciones = opciones;
    }
}
