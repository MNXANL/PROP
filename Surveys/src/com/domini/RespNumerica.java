package com.domini;

/**
 * una respuesta que consiste en un sólo valor float
 */
public class RespNumerica extends Respuesta{
    private float resp;
    private float min, max;

    /**
     * @param f
     */
    public RespNumerica(float f, float min, float max){
        resp=f;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return
     */
    public float get(){
        return resp;
    }

    /**
     *
     * @param f
     */
    public void set(float f){
        resp=f;
    }

    /**
     * distancia entre dos respuestas numericas
     * @param r respuesta a comparar
     * @return valor entre 0 y 1 que representa la distancia
     */
    public double distance (RespNumerica r){
        return Math.abs(r.resp -resp)/(max-min);
    }
}
