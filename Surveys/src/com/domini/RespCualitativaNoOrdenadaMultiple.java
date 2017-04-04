package com.domini;
import java.util.*;

/**
 * un conjunto de números que corresponde a las opciones seleccionadas por el usuario
 */

public class RespCualitativaNoOrdenadaMultiple extends Respuesta{

    private HashSet<Integer> seleccion;

    /**
     *
     * @param seleccion
     */
    public RespCualitativaNoOrdenadaMultiple(HashSet<Integer> seleccion){
        this.seleccion=seleccion;
    }

    /**
     *
     * @return
     */
    public HashSet<Integer> get(){
        return seleccion;
    }

    /**
     *
     * @param seleccion
     */
    public void set(HashSet<Integer> seleccion){
        this.seleccion=seleccion;
    }
}
