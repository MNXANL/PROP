package tests.domini.RespVacia;

import com.domini.RespNumerica;
import com.domini.Respuesta;

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
    @Override
    public boolean equals(Object o){
        if(!(o instanceof RespVacia))
            return false;
        return true;
    }
    @Override
    public int hashCode(){
        return 42;
    }
}
