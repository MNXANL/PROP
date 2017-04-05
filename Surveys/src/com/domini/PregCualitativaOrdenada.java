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
     *
     */
    public void PosiblesRespuestas(){
        for (int i : opciones) {
            System.out.println(i + ": " + opciones[i]);
        }
    }
}
