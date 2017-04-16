package com.domini;

import java.util.Objects;

/**
 * una respuesta que consiste en un sólo valor double
 */
public class RespNumerica extends Respuesta{
    private double resp;
    private double min, max;

    /**
     * @param f
     */
    public RespNumerica(double f, double min, double max){
        resp=f;
        this.min = min;
        this.max = max;
    }

    /**
     * creador de copia
     * @param r
     */
    public RespNumerica(RespNumerica r){
        this.resp = r.resp;
        this.min = r.min;
        this.max = r.max;
    }

    /**
     *
     * @return
     */
    public double get(){
        return resp;
    }

    /**
     *
     * @param f
     */
    public void set(double f){
        resp=f;
    }

    /**
     * distancia entre dos respuestas numericas
     * @param x respuesta a comparar
     * @return valor entre 0 y 1 que representa la distancia
     */
    public double distance (Respuesta x){
        RespNumerica r = (RespNumerica) x;
        return Math.abs(r.resp - resp)/(max-min);
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespNumerica))
            return false;
        RespNumerica r = (RespNumerica) o;

        return r.resp==resp && r.max == max && r.min == min;
    }
    @Override
    public int hashCode(){
        return Objects.hash(resp,min,max);
    }

}
