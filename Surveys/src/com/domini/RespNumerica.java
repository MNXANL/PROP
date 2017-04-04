package com.domini;

/**
 * una respuesta que consiste en un sólo valor float
 */
public class RespNumerica extends Respuesta{
    private float resp;

    /**
     * @param f
     */
    public RespNumerica(float f){
        resp=f;
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
}
