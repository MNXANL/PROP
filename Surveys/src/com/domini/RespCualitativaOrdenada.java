package com.domini;

/**
 * una selección de entre k opciones ordenadas
 */
public class RespCualitativaOrdenada extends Respuesta {

    private int seleccion;

    /**
     *
     * @param n
     */
    public RespCualitativaOrdenada(int n){
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
