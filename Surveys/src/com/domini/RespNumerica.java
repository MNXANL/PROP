package com.domini;

import java.util.Objects;

/**
 * una respuesta que consiste en un sólo valor double
 */
public class RespNumerica extends Respuesta{
    private double resp;
    private double min, max;

    /**
     * creadora
     * @param f valor respondido
     * @param min valor maximo posible
     * @param max valor minimo posible
     */
    public RespNumerica(double f, double min, double max){
        resp=f;
        this.min = min;
        this.max = max;
    }

    /**
     * creador de copia
     * @param r RespNumerica a copiar
     */
    public RespNumerica(RespNumerica r){
        this.resp = r.resp;
        this.min = r.min;
        this.max = r.max;
    }

    /**
     * Obtener valor de respuesta
     * @return respuesta
     */
    public double get(){
        return resp;
    }

    /**
     *Obtener valor minimo posible
     * @return minimo valor minimo posible respuesta
     */
    public double getMin(){
        return min;
    }

    /**
     * Obtener valor maximo posible
     * @return maximo valor maximo posible respuesta
     */
    public double getMax(){
        return max;
    }

    /**
     * Fijar valor de respuesta. Debe estar entre el valor minimo y el maximo
     * @param f respuesta
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

    /**
     * Metodo para comparar la clase con otro objeto
     * @param o Objeto a comparar
     * @return Si los objetos son los mismos o no
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespNumerica))
            return false;
        RespNumerica r = (RespNumerica) o;

        return r.resp==resp && r.max == max && r.min == min;
    }

    /**
     * Metodo para obtener el codigo de hash
     * @return El codigo de hash
     */
    @Override
    public int hashCode(){
        return Objects.hash(resp,min,max);
    }

}
