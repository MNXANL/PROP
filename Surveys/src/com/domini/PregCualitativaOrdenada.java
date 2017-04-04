package com.domini;

import java.util.ArrayList;

/**
 * Clase que guarda preguntas cualitativas ordenadas
 */
public class PregCualitativaOrdenada extends ArrayList {
    ArrayList<String> opciones;

    /**
     * Constructora de la clase
     * @param opciones Opciones ordenadas a escoger
     */
    public PregCualitativaOrdenada(ArrayList<String> opciones) {
        this.opciones = opciones;
    }
}
