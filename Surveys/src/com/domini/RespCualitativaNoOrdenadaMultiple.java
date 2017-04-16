package com.domini;
import java.util.*;

/**
 * un conjunto de números que corresponde a las opciones seleccionadas por el usuario
 */

public class RespCualitativaNoOrdenadaMultiple extends Respuesta{

    //integer = seleccion, string = contenido de la seleccion, por conveniencia
    private HashMap<Integer, String> seleccion;

    /**
     *
     * @param seleccion
     */
    public RespCualitativaNoOrdenadaMultiple(HashMap<Integer, String> seleccion){
        this.seleccion=seleccion;
    }

    /**
     *
     * @return
     */
    public HashSet<Integer> get(){
        return new HashSet<>(seleccion.keySet());
    }

    public HashMap<Integer, String> getMap(){
        return seleccion;
    }

    /**
     *
     * @param seleccion
     */
    public void set(HashMap<Integer, String> seleccion){
        this.seleccion = seleccion;
    }

    /**
     * devuelve la distancia en el intervalo [0,1]  de r a esta respuesta
     * @param x Respuesta hasta la cual hay que medir la "distancia"
     * @return un double entre 0 y 1
     */
    public double distance (Respuesta x){
        RespCualitativaNoOrdenadaMultiple r = (RespCualitativaNoOrdenadaMultiple) x;
        HashSet<Integer> intersection = new HashSet<>(seleccion.keySet());
        intersection.retainAll(r.seleccion.keySet()); //intersection contiene los elementos que tienen ambos sets en comun
        HashSet<Integer> union = new HashSet<>(seleccion.keySet());
        union.addAll(r.seleccion.keySet());

        double jaccard;
        if (union.size()==0) jaccard = 1;
        else{
            jaccard = (intersection.size()/union.size());
        }
        return (1-jaccard);
    }

}
