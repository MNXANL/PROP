package com.domini;

/**
 * Clase encargada de realizar el analisis y clustering de las respuestas a una encuesta
 */
public class Clustering {
    Encuesta E;
    int k;

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
