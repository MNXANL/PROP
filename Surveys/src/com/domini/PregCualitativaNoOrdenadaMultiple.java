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
     * @param titulo Titulo de la pregunta
     * @param opciones El conjunto de opciones de la encuesta.
     */
    public PregCualitativaNoOrdenadaMultiple(String titulo, ArrayList<String> opciones) {
        super(titulo);
        this.opciones = opciones;
        maxOpciones = opciones.size(); //Si fuese igual a 1, no formaría parte de esta clase!
    }

    /**
     * Constructora de la clase, con mínimo 2 opciones
     * @param titulo Titulo de la pregunta
     * @param opciones El conjunto de opciones de la encuesta
     * @param maxOpciones . Su valor debería ser 1 < maxOpciones <= que opciones.size()
     */
    public PregCualitativaNoOrdenadaMultiple(String titulo, ArrayList<String> opciones, int maxOpciones) {
        super(titulo);
        this.opciones = opciones;
        this.maxOpciones = maxOpciones;
    }

    /**
     * Obtener el tipo de la pregunta
     * @return Tipo de la pregunta
     */
    public String tipo () {
        return "PCNOM";
    }

    /**
     * Obtener toda la información de la pregunta a excepción del título
     * @return String con el contenido de la pregunta
     */
    public String getContenido () {
        String contenido = maxOpciones+"\n";
        for (int i = 0; i < opciones.size(); i++) {
            contenido += /*i + ") " +*/ opciones.get(i) + "\n";
        }

        return contenido;
    }

    /**
     * Metodo para obtener pregunta iesima
     * @param i indice de la pregunta
     * @return La pregunta en la posicion i
     */
    public String getPreguntaIesima(int i) {
        return opciones.get(i);
    }

    /**
     * Metodo para escribir por consola el contenido de la pregunta
     */
    public void leer () {
        System.out.println(getTitulo());
        for(int i = 0; i < opciones.size(); i++) {
            System.out.println(opciones.get(i));
        }
        System.out.println(maxOpciones);
    }

    /**
     * Obtener max. opciones que un usuario puede contestar
     * @return maximo numero de opciones
     */
    public int getMaxOptions() {
        return maxOpciones;
    }


    /**
     * Obtener tamanyo preguntas
     * @return maximo numero de preguntas
     */
    public int getSize() {
        return opciones.size();
    }
}
