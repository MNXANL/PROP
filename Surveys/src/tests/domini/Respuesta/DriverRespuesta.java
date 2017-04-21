package tests.domini.Respuesta;

import com.domini.RespLibre;
import tests.domini.RespVacia.RespVacia;

import java.util.Scanner;

/**
 * Driver de Respuesta
 */
public class DriverRespuesta {

    private static Respuesta r;

    public static double testDistance(com.domini.Respuesta r) {
        return r.distance(r);
    }


    public static boolean testEquals(Object o) {
        return r.equals(o);
    }


    public int hashCode() {
        return r.hashCode();
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Creada respuesta.");

        System.out.println("Al tratarse de una clase abstracta, no se pueden probar directamente sus metodos. " +
                "Si quieres probarlos, hazlo en una de sus subclase | Resp*");

    }
}
