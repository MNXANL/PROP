package com.domini;

import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;

import java.util.ArrayList;
import java.util.HashMap;
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
     * @param k Numero de centroids pedidas
     */
    public Clustering(Encuesta E, int k){
        this.E = new Encuesta(E);
        this.k = k;
    }

    /**
     * correr el analisis kmeans, primero generando centroids iniciales antes de llamar al algoritmo
     */
    public void run(){
        ArrayList<RespuestasEncuesta> centroids = new ArrayList<>();  //guarda los indices de los centroids iniciales, que seran conjuntos de respuestas de usuarios al azar
        ArrayList<RespuestasEncuesta> RE = E.getCjtRespsEnc();

        Random rand = new Random();
        while(centroids.size() < k ){        //llenar el set de centroids iniciales con RespuestasEncuesta random de la encuesta
            int i = Math.abs(rand.nextInt()%RE.size());
            System.out.println("value of i: " + i);
            RespuestasEncuesta aux = RE.get(i);
            if(!centroids.contains(aux)) centroids.add(aux);
        }
        Kmeans(RE,centroids);
    }
    private void Kmeans(ArrayList<RespuestasEncuesta> RE, ArrayList<RespuestasEncuesta> centroids){

        ArrayList<Integer> assig = new ArrayList<>(); //assig[i] = número del cluster al que pertenece RE[i]
        while(assig.size()< RE.size()) assig.add(-1); //inicializar assig

        for(int i = 0; i != RE.size(); ++i){
            RespuestasEncuesta r = RE.get(i);
            double dist = 2;            //lo pongo a dos para que siempre coja el primero
            for(int j = 0; j!=centroids.size(); ++j){    //asignar cada respuesta a su centroide mas cercano
                RespuestasEncuesta cl = centroids.get(j);
                if (answer_dist(r,cl) < dist) {
                    assig.set(i, j);
                    dist = answer_dist(r,cl);
                }
            }
        }
        //recalcular centroides

        boolean change = false;
        for(int i = 0; i!= centroids.size(); ++i){ //por cada centroide de un cluster
            RespuestasEncuesta newCentroid = new RespuestasEncuesta(E, "Centroid " + i);
            ArrayList<Respuesta> resps = centroids.get(i).resps;

            for(int k = 0; k != resps.size(); ++k){  // por cada Respuesta del conjunto
                if(resps.get(k) instanceof RespNumerica){
                    newCentroid.resps.add(Respnum_avg(i,k, assig, RE));
                }
                if(resps.get(k) instanceof RespCualitativaNoOrdenadaUnica){
                    newCentroid.resps.add(RespCNOU_mode(i,k,assig,RE));
                }
                if(resps.get(k) instanceof RespCualitativaOrdenada){
                    newCentroid.resps.add(RespCO_mode(i,k,assig,RE));
                }
                if(resps.get(k) instanceof RespCualitativaNoOrdenadaMultiple){
                    newCentroid.resps.add(RespMUL_maxfreq(i,k,assig,RE));
                }

            }
            RespuestasEncuesta oldCentroid = centroids.get(i);
            if(!oldCentroid.equals(newCentroid))
                change=true;
            centroids.set(i,newCentroid);
        }
        if(!change){ //el algoritmo ha acabado si los centroides no cambian
            //de momento hacemos un output de assig para probar
            System.out.println("asignacion de clusters");
            for(int i : assig) System.out.println(i);
        }
        else {
            System.out.println("Do the K means");
            Kmeans(RE,centroids);
        }

    }

    /**
     * devuelve la Respuesta que contiene la seleccion mas comun
     * @param cli indice del cluster que estamos tratando
     * @param rn numero de la respuesta dentro de RespuestasEncuesta
     * @param assig vector que mapea los elementos de RE a un cluster
     * @param RE lista de respuestas que han dado los encuestados
     * @return
     */
    private RespCualitativaNoOrdenadaMultiple
    RespMUL_maxfreq(int cli, int rn,ArrayList<Integer> assig, ArrayList<RespuestasEncuesta> RE ){

        int maxCount;
        maxCount=-1;
        HashMap<Integer,String> maxValue = new HashMap<>();
        for(int i = 0; i != RE.size(); ++i){
            if(assig.get(i)==cli){      //solo tratamos los que pertenecen al cluster que nos piden
                int count = 0;
                RespCualitativaNoOrdenadaMultiple r = (RespCualitativaNoOrdenadaMultiple) RE.get(i).resps.get(rn);
                for(int j = 0; j!= RE.size(); ++j){
                    if(assig.get(j)==cli){
                        RespCualitativaNoOrdenadaMultiple aux = (RespCualitativaNoOrdenadaMultiple) RE.get(j).resps.get(rn);
                        HashSet<Integer> intersection = new HashSet<>(r.get());
                        intersection.retainAll(aux.get());
                        if(intersection.size() == r.get().size()) ++count;
                    }
                }
                if(count > maxCount){
                    maxCount = count;
                    maxValue = new HashMap<Integer,String>(r.getMap());
                }

            }
        }
        return new RespCualitativaNoOrdenadaMultiple(maxValue);
    }

    /**
     * devuelve la Respuesta que contiene la moda de entre todas las respuestas
     * @param cli indice del cluster que estamos tratando
     * @param rn numero de la respuesta dentro de RespuestasEncuesta
     * @param assig vector que mapea los elementos de RE a un cluster
     * @param RE lista de respuestas que han dado los encuestados
     * @return
     */
    private RespCualitativaNoOrdenadaUnica RespCNOU_mode(int cli, int rn,  ArrayList<Integer> assig, ArrayList<RespuestasEncuesta> RE){

        int maxValue, maxCount;
        maxCount = maxValue = -1;
        String text = "";
        for(int i = 0; i != RE.size(); ++i){
            if(assig.get(i) == cli){      //solo tratamos los que pertenecen al cluster que nos piden
                int count = 0;
                RespCualitativaNoOrdenadaUnica r = (RespCualitativaNoOrdenadaUnica) RE.get(i).resps.get(rn);
                for(int j = 0; j!= RE.size(); ++j){
                    if(assig.get(j)==cli){
                        RespCualitativaNoOrdenadaUnica aux = (RespCualitativaNoOrdenadaUnica) RE.get(j).resps.get(rn);
                        if (r.get()==aux.get()) ++count;
                    }
                }
                if(count > maxCount){
                    maxCount = count;
                    maxValue = r.get();
                    text = r.getTxt();
                }

            }
        }
        return new RespCualitativaNoOrdenadaUnica(maxValue, text);
    }
    /**
     * devuelve la Respuesta que contiene la moda de entre todas las respuestas
     * @param cli indice del cluster que estamos tratando
     * @param rn numero de la respuesta dentro de RespuestasEncuesta
     * @param assig vector que mapea los elementos de RE a un cluster
     * @param RE lista de respuestas que han dado los encuestados
     * @return
     */
    private RespCualitativaOrdenada
    RespCO_mode(int cli, int rn,  ArrayList<Integer> assig, ArrayList<RespuestasEncuesta> RE){
        RespCualitativaOrdenada x =(RespCualitativaOrdenada) RE.get(0).resps.get(rn);
        int maxValue,maxCount;
        maxCount=maxValue=-1;
        for(int i = 0; i != RE.size(); ++i){
            if(assig.get(i)==cli){      //solo tratamos los que pertenecen al cluster que nos piden
                int count = 0;
                RespCualitativaOrdenada r = (RespCualitativaOrdenada) RE.get(i).resps.get(rn);
                for(int j = 0; j!= RE.size(); ++j){
                    if(assig.get(j)==cli){
                        RespCualitativaOrdenada aux = (RespCualitativaOrdenada) RE.get(j).resps.get(rn);
                        if(r.get()==aux.get()) ++count;
                    }
                }
                if(count > maxCount){
                    maxCount = count;
                    maxValue = r.get();
                }

            }
        }
        x.set(maxValue);
        return new RespCualitativaOrdenada(x);
    }
    /**
     * devuelve la RespNumerica que es la media de todas las respuestas numericas de un grupo de RespuestasEncuesta
     * @param cli indice del cluster que estamos tratando
     * @param rn numero de la respuesta dentro de RespuestasEncuesta
     * @param assig vector que mapea los elementos de RE a un cluster
     * @param RE lista de respuestas que han dado los encuestados
     * @return
     */
    private RespNumerica Respnum_avg (int cli, int rn,  ArrayList<Integer> assig, ArrayList<RespuestasEncuesta> RE){
        double sum,count;
        sum = count = 0;
        RespNumerica result =(RespNumerica) RE.get(0).resps.get(rn);
        for(int i = 0; i != RE.size(); ++i){
            if(assig.get(i)==cli){      //solo tratamos los que pertenecen al cluster que nos piden
                ++count;
                RespNumerica aux = (RespNumerica) RE.get(i).resps.get(rn);
                sum += aux.get();
            }
        }
        result.set(sum/count);
        return new RespNumerica(result);
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
            else {
                acc += r1.resps.get(i).distance(r2.resps.get(i));
                RespNumerica a =(RespNumerica) r1.resps.get(i);
                RespNumerica b =(RespNumerica) r2.resps.get(i);
                System.out.println("r1: " + a.get());
                System.out.println("r2: " + b.get());
                System.out.println("acc after: "+acc);
            }

        }
        System.out.println ("Distance: "+acc/r1.resps.size());
        return acc/r1.resps.size();
    }
}
