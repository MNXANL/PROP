package com.domini;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Objects;

/**
 * una seleccion de entre k opciones sin ningun orden implícito
 */
public class RespCualitativaNoOrdenadaUnica extends Respuesta{

    private int seleccion;
    private String textoSelec;

    /**
     * Constructora
     * @param n numero de la opcion seleccionada
     */
    public RespCualitativaNoOrdenadaUnica(int n, String txt){
        seleccion = n;
        textoSelec = txt;
    }

    /**
     * Obtener indice de respuesta
     * @return seleccion de respuesta
     */
    public int get(){
        return seleccion;
    }

    /**
     * Obtener texto seleccionado
     * @return el string correspondiente a la seleccion
     */
    public String getText(){
        return textoSelec;
    }

    /**
     * Fijar texto y numero de seleccion
     * @param n seleccion
     */
    public void set(int n, String txt){
        seleccion = n;
        textoSelec = txt;
    }

    /**
     * devuelve la distancia entre dos respuestas de este tipo
     * @param x respuesta a comparar
     * @return 1 si son la misma respuesta, 0 sino
     */
    public double distance(Respuesta x){
        RespCualitativaNoOrdenadaUnica re = (RespCualitativaNoOrdenadaUnica) x;
        if (re.seleccion == seleccion) return 0;
        else return 1;
    }

    /**
     * Metodo para comparar la clase con otro objeto
     * @param o Objeto a comparar
     * @return Si los objetos son los mismos o no
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespCualitativaNoOrdenadaUnica))
            return false;
        RespCualitativaNoOrdenadaUnica r = (RespCualitativaNoOrdenadaUnica) o;

        return r.seleccion==seleccion;
    }

    /**
     * Metodo para obtener el codigo de hash
     * @return El codigo de hash
     */
    @Override
    public int hashCode(){
        return Objects.hash(seleccion);
    }
}
