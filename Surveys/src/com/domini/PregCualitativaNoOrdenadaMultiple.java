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
    public PregCualitativaNoOrdenadaMultiple(String titulo, ArrayList<String> opciones) {
        super(titulo);
        this.opciones = opciones;
        maxOpciones = 2; //Si fuese igual a 1, no formaría parte de esta clase!
    }

    /**
     * Constructora de la clase, con
     * @param opciones El conjunto de opciones de la encuesta
     * @param maxOpciones . Su valor debería ser 1 < maxOpciones <= opciones.size()
     */
    public PregCualitativaNoOrdenadaMultiple(String titulo, ArrayList<String> opciones, int maxOpciones) {
        super(titulo);
        this.opciones = opciones;
        this.maxOpciones = maxOpciones;
    }

    /**
     * Obtener el tipo de la pregunta
     * @return
     */
    public String tipo () {
        return "PCNOM";
    }

    /**
     * Obtener toda la información de la pregunta a excepción del título
     * @return
     */
    public String getContenido () {
        String contenido = maxOpciones+"\n";
        for (int i = 0; i < opciones.size(); i++) {
            contenido += i + ") " + opciones.get(i) + "\n";
        }

        return contenido;
    }

    public void leer () {
        System.out.println(getTitulo());
        for(int i = 0; i < opciones.size(); i++) {
            System.out.println(opciones.get(i));
        }
        System.out.println(maxOpciones);
    }

    public int getMaxOptions() {
        return maxOpciones;
    }
}
