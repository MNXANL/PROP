package com.domini;

/**
 * una selección de entre k opciones ordenadas
 */
public class RespCualitativaOrdenada extends Respuesta {

    private int seleccion;
    private int noptions;

    /**
     *
     * @param ns numero de la seleccion que ha hecho el usuario
     * @param nopts numero de opcciones totales
     */
    public RespCualitativaOrdenada(int ns,int nopts){
        seleccion = ns;
        noptions=nopts;
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
     * distancia entre dos respuestas cualitativas ordenadas
     * @param x respuesta a comparar
     * @return valor entre 0 y 1 que representa la distancia
     */
    public double distance(Respuesta x){
        RespCualitativaOrdenada re = (RespCualitativaOrdenada) x;
        if(noptions == 1) return 1;
        else{
            return Math.abs(re.seleccion-seleccion)/(noptions-1);
        }
    }


    public String tipo () {
        return "PCO";
    }
}
