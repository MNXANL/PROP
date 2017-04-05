package com.domini;

//respuesta libre, un string conteniendo cualquier cosa
public class RespLibre extends Respuesta{
    private String resp;

    /**
     *
     * @param s
     */

    public RespLibre(String s){
        resp=s;
    }

    /**
     *
     * @return
     */
    public String get(){
        return resp;
    }

    /**
     *
     * @param s
     */
    public void set(String s){
        resp=s;
    }

    public double distance (RespLibre r){
        return 1;
    }
}
