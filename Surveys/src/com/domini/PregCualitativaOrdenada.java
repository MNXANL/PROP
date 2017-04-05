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
    public PregCualitativaOrdenada(ArrayList<String> opciones) {
        super(Pregunta);
        this.opciones = opciones;
    }
}
