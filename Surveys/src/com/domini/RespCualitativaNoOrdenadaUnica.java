package com.domini;

import com.sun.org.apache.regexp.internal.RE;

/**
 * una seleccion de entre k opciones sin ningun orden implícito
 */
public class RespCualitativaNoOrdenadaUnica extends Respuesta{

    private int seleccion;
    private String textoSelec;

    /**
     *
     * @param n
     */
    public RespCualitativaNoOrdenadaUnica(int n, String txt){
        seleccion = n;
        textoSelec = txt;
    }

    /**
     *
     * @return
     */
    public int get(){
        return seleccion;
    }

    /**
     *
     * @return
     */
    public String getTxt(){
        return textoSelec;
    }

    /**
     *
     * @param n
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
        if (re.seleccion == seleccion) return 1;
        else return 0;
    }

}
