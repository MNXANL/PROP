package com.domini;

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
        this.E = new Encuesta(E);
        this.k = k;
    }

    /**
     * devuelve la distancia total entre el conjunto de respuestas de un usuario y otro
     * @param r1 conjunto de respuestas de un usuario 1
     * @param r2 conjunto de respuestas de un usuario 2
     * @return la distancia entre ambos
     */
    private double answer_dist(RespuestasEncuesta r1, RespuestasEncuesta r2){
        double acc = 0;
        for (int i = 0; i != r1.resps.size(); ++i){
            acc += r1.resps.get(i).distance(r2.resps.get(i));
        }
    }
}
