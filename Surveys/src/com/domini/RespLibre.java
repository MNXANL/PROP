package com.domini;

//respuesta libre, un string conteniendo cualquier cosa
public class RespLibre extends Respuesta{
    private String resp;

    public RespLibre(String s){
        resp=s;
    }
    public String get(){
        return resp;
    }
    public void set(String s){
        resp=s;
    }
}
