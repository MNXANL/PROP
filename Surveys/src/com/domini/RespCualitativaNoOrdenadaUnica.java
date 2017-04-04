package com.domini;

/**
 * una seleccion de entre k opciones sin ningun orden implícito
 */
public class RespCualitativaNoOrdenadaUnica extends Respuesta{

    private int seleccion;

    /**
     *
     * @param n
     */
    public RespCualitativaNoOrdenadaUnica(int n){
        seleccion = n;
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
     * @param n
     */
    public void set(int n){
        seleccion = n;
    }
}
