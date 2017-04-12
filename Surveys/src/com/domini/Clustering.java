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
        ArrayList<RespuestasEncuesta> clusters = new ArrayList<>();  //guarda los indices de los clusters iniciales, que seran conjuntos de respuestas de usuarios al azar
        ArrayList<RespuestasEncuesta> RE = E.getX();

        Random rand = new Random();
        while(clusters.size() < k ){        //llenar el set de clusters iniciales con RespuestasEncuesta random de la encuesta
            int i = rand.nextInt()%RE.size();
            RespuestasEncuesta aux = RE.get(i);
            if(!clusters.contains(aux)) clusters.add(aux);
        }
        Kmeans(RE,clusters);
    }
    private void Kmeans(ArrayList<RespuestasEncuesta> RE, ArrayList<RespuestasEncuesta> clusters){
        ArrayList<Integer> assig = new ArrayList<>(RE.size()); //assig[i] = número del cluster al que pertenece RE[i]
        for(int i = 0; i != RE.size(); ++i){
            RespuestasEncuesta r = RE.get(i);
            double dist = 2;            //lo pongo a dos para que siempre coja el primero
            for(int j = 0; j!=clusters.size(); ++j){    //asignar cada respuesta a su centroide mas cercano
                RespuestasEncuesta cl = clusters.get(j);
                if (answer_dist(r,cl) < dist)
                    assig.set(i,j);
            }
        }
        //recalcular centroides

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
