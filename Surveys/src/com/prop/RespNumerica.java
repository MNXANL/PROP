package com.prop;

//una respuesta que consiste en un sólo valor float
public class RespNumerica extends Respuesta{

    private float resp;

    public RespNumerica(float f){
        resp=f;
    }
    public float get(){
        return resp;
    }
    public void set(float f){
        resp=f;
    }
}
