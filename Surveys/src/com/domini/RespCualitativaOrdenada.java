package com.domini;

//una selección de entre k opciones ordenadas
public class RespCualitativaOrdenada extends Respuesta {

    private int seleccion;

    public RespCualitativaOrdenada(int n){
        seleccion = n;
    }
    public int get(){
        return seleccion;
    }
    public void set(int n){
        seleccion = n;
    }
}
