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

    /**
     * devuelve la distancia entre dos respuestas de este tipo
     * @param re respuesta a comparar
     * @return 1 si son la misma respuesta, 0 sino
     */
    public double distance(RespCualitativaNoOrdenadaUnica re){
        if(re.seleccion==seleccion) return 1;
        else return 0;
    }
}
