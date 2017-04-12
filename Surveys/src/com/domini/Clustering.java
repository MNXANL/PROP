package com.domini;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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
     * correr el analisis kmeans, primero generando clusters iniciales antes de llamar al algoritmo
     */
    public void run(){
        HashSet<RespuestasEncuesta> clusters = new HashSet<>();  //guarda los indices de los clusters iniciales, que seran conjuntos de respuestas de usuarios al azar
        ArrayList<RespuestasEncuesta> RE = E.getX();

        Random rand = new Random();
        while(clusters.size() < k ){        //llenar el set de clusters iniciales con RespuestasEncuesta random de la encuesta
            int i = rand.nextInt()%RE.size();
            RespuestasEncuesta aux = RE.get(i);
            if(!clusters.contains(aux)) clusters.add(aux);
        }
        Kmeans(RE,clusters);
    }
    private void Kmeans(ArrayList<RespuestasEncuesta> RE, HashSet<RespuestasEncuesta> clusters){

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
            if(r2.resps.get(i) instanceof RespVacia && !(r1.resps.get(i) instanceof RespVacia))
                acc += 1;
            else
                acc += r1.resps.get(i).distance(r2.resps.get(i));
        }
        return acc/r1.resps.size();
    }
}
