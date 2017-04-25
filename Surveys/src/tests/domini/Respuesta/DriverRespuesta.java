package tests.domini.Respuesta;

import com.domini.RespLibre;
import tests.domini.RespVacia.RespVacia;

import java.util.Scanner;

/**
 * Driver de Respuesta
 */
public class DriverRespuesta {

    private Respuesta r;

    public double testDistance(com.domini.Respuesta r) {
        return r.distance(r);
    }


    public boolean testEquals(Object o) {
        return r.equals(o);
    }


    public int hashCode() {
        return r.hashCode();
    }

    public static void main (String [] arg) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Creada respuesta.");

        System.out.println("Al tratarse de una clase abstracta, no se pueden probar directamente sus metodos. " +
                "Si quieres probarlos, hazlo en una de sus subclase | Resp*");

    }
}
