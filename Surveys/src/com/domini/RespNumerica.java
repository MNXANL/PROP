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
        try {
            if (min > max || f < min || f > max)
                throw new IllegalArgumentException("Fuera de rango!");
            resp=f;
            this.min = min;
            this.max = max;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Numeros fuera de rango!");
        }
    }

    /**
     * creador de copia
     * @param r RespNumerica a copiar
     */
    public RespNumerica(RespNumerica r){
        try {
            if (r.min > r.max || r.resp < r.min || r.resp > r.max)
                throw new IllegalArgumentException("Fuera de rango!");
            this.resp = r.resp;
            this.min = r.min;
            this.max = r.max;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Numeros fuera de rango!");
        }
    }

    /**
     *
     * @return respuesta
     */
    public double get(){
        return resp;
    }
    /**
     *
     * @return minimo
     */
    public double getMin(){
        return min;
    }
    /**
     *
     * @return maximo
     */
    public double getMax(){
        return max;
    }

    /**
     *
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
