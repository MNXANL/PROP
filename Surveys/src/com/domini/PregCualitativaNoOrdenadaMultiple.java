package com.domini;

import java.util.ArrayList;

/**
 * Created by mike on 3/28/17.
 */
public class PregCualitativaNoOrdenadaMultiple extends ArrayList {
    private ArrayList<String> opciones;
    private int maxOpciones;

    public PregCualitativaNoOrdenadaMultiple(ArrayList<String> opciones) {
        this.opciones = opciones;
        maxOpciones = 2; //Si fuese igual a 1, no formaría parte de esta clase!
    }

    public PregCualitativaNoOrdenadaMultiple(ArrayList<String> opciones, int maxOpciones) {
        this.opciones = opciones;
        this.maxOpciones = maxOpciones;
    }
}
