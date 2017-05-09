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
     *
     * @param n numero de la opcion seleccionada
     */
    public RespCualitativaNoOrdenadaUnica(int n, String txt){
        seleccion = n;
        textoSelec = txt;
    }

    /**
     *
     * @return la seleccion
     */
    public int get(){
        return seleccion;
    }

    /**
     *
     * @return el string correspondiente a la seleccion
     */
    public String getText(){
        return textoSelec;
    }

    /**
     *
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
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespCualitativaNoOrdenadaUnica))
            return false;
        RespCualitativaNoOrdenadaUnica r = (RespCualitativaNoOrdenadaUnica) o;

        return r.seleccion==seleccion;
    }
    @Override
    public int hashCode(){
        return Objects.hash(seleccion);
    }
}
