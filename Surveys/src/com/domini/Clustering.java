package com.domini;

import java.util.ArrayList;

/**
 * Clase encargada de realizar el analisis y clustering de las respuestas a una encuesta
 */
public class Clustering {
    private Encuesta E;
    private int k;
    /**
     *Creadora de clustering
     * @param E Encuesta sobre la que realizar el clustering
     * @param k Numero de clusters pedidas
     */
    public Clustering(Encuesta E, int k){
        this.E = E;
        this.k = k;
    }
}
