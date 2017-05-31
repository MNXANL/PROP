package com.domini;

/**
 * clase que sirve para representar una respuesta que no ha sido contestada
 */
public class RespVacia extends Respuesta{

    /**
     * Metodo para obtener la distancia entre respuesta vacia y cualquier otra
     * @param r
     * @return distancia entre respuesta vacia y cualquier otra
     */
    public double distance(Respuesta r){
        if(r instanceof RespVacia)
            return 0;
        else
            return 1;
    }

    /**
     * Metodo para comparar la clase con otro objeto
     * @param o Objeto a comparar
     * @return Si los objetos son los mismos o no
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof RespVacia) return true;
        else return false;
    }

    /**
     * Metodo para obtener el codigo de hash
     * @return El codigo de hash
     */
    @Override
    public int hashCode(){
        return 42;
    }
}
