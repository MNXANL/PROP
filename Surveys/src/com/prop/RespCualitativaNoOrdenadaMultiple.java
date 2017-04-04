package com.prop;//un conjunto de números que corresponde a las opciones seleccionadas por el usuario
import java.util.*;
public class RespCualitativaNoOrdenadaMultiple extends Respuesta{

    private HashSet<Integer> seleccion;

    public RespCualitativaNoOrdenadaMultiple(HashSet<Integer> seleccion){
        this.seleccion=seleccion;
    }
    public HashSet<Integer> get(){
        return seleccion;
    }
    public void set(HashSet<Integer> seleccion){
        this.seleccion=seleccion;
    }
}
