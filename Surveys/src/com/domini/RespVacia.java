package com.domini;

/**
 * clase que sirve para representar una respuesta que no ha sido contestada
 */
public class RespVacia extends Respuesta{

    /**
     * distancia entre respuesta vacia y cualquier otra
     * @param r
     * @return
     */
    public double distance(Respuesta r){
        if(r instanceof RespVacia)
            return 0;
        else
            return 1;
    }
}
