package com.domini;

import java.util.ArrayList;

/**
 * Clase que guarda preguntas cualitativas no ordenadas, con multiples opciones a escoger.
 */
public class PregCualitativaNoOrdenadaMultiple extends Pregunta {
    private ArrayList<String> opciones;
    private int maxOpciones;

    /**
     * Constructora de la clase, con mínimo 2 opciones
     * @param opciones El conjunto de opciones de la encuesta.
     */
    public PregCualitativaNoOrdenadaMultiple(ArrayList<String> opciones) {
        super(Pregunta);
        this.opciones = opciones;
        maxOpciones = 2; //Si fuese igual a 1, no formaría parte de esta clase!
    }

    /**
     * Constructora de la clase, con
     * @param opciones El conjunto de opciones de la encuesta
     * @param maxOpciones . Su valor debería ser 1 < maxOpciones <= opciones.size()
     */
    public PregCualitativaNoOrdenadaMultiple(ArrayList<String> opciones, int maxOpciones) {
        super(Pregunta);
        this.opciones = opciones;
        this.maxOpciones = maxOpciones;
    }
}
