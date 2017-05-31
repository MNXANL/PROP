package com.domini;

import java.util.ArrayList;

/**
 * Clase que guarda preguntas cualitativas ordenadas
 */
public class PregCualitativaOrdenada extends Pregunta {
    ArrayList<String> opciones;

    /**
     * Constructora de la clase
     * @param opciones Opciones ordenadas a escoger
     */
    public PregCualitativaOrdenada(String titulo, ArrayList<String> opciones) {
        super(titulo);
        this.opciones = opciones;
    }

    /**
     * Obtener el tipo de la pregunta
     * @return
     */
    public String tipo () {
        return "PCO";
    }

    /**
     * Obtener toda la información de la pregunta a expeción del título
     * @return
     */
    public String getContenido () {
        String contenido = "";
        for (int i = 0; i < opciones.size(); i++) {
            contenido += /*i + ") " +*/ opciones.get(i) + "\n";
        }
        return contenido;
    }

    /**
     * Metodo para escribir por consola el contenido de la pregunta
     */
    public void leer () {
        System.out.println(getTitulo());
        for(int i = 0; i < opciones.size(); i++) {
            System.out.println(opciones.get(i));
        }
    }

    /**
     * Obtener tamanyo preguntas
     * @return maximo numero de preguntas
     */
    public int getSize() {
        return opciones.size();
    }

    /**
     * Metodo para obtener pregunta iesima
     * @param i indice de la pregunta
     * @return La pregunta en la posicion i
     */
    public String getPreguntaIesima(int i) {
        return opciones.get(i);
    }
}
