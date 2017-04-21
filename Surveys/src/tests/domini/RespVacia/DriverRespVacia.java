package tests.domini.RespVacia;


import com.domini.RespLibre;
import com.domini.Respuesta;

import java.util.Scanner;

/**
 * Driver de RespVacia
 */
public class DriverRespVacia {
    private static RespVacia r;

    public static double testDistance(Respuesta rr) {
        return r.distance(rr);
    }


    public static boolean testEquals(Object o) {
        return r.equals(o);
    }


    public static int testhashCode() {
        return r.hashCode();
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Creando respuesta vacía: ");
        r = new RespVacia();
        System.out.println("Creada.");

        System.out.println("1. Ver distancia de respuesta vacía respecto respuesta vacía");
        System.out.println("2. Ver distancia de respuesta vacía respecto respuesta NO vacía");
        System.out.println("3. Comprobar que es igual a otra respuesta vacía");
        System.out.println("3. Comprobar que es igual a otra respuesta NO vacía");
        System.out.println("4. Retornar codigo de Hash");
        System.out.println("5. Salir del test");

        boolean inTest = true;
        while (inTest) {
            switch (sc.nextInt()) {
                case 1:
                    Respuesta s1 = new RespVacia();
                    System.out.println("Distance = " + testDistance(s1));
                    break;
                case 2:
                    Respuesta s2 = new RespLibre("");
                    System.out.println("Distance = " + testDistance(s2));
                    break;
                case 3:
                    Respuesta s3 = new RespVacia();
                    testEquals(s3);
                    break;
                case 4:
                    Respuesta s4 = new RespLibre("");
                    boolean b = testEquals(s4);
                    if (b) System.out.println("TRUE");
                    else   System.out.println("FALSE");
                    break;
                case 5:
                    System.out.println("HashCode = " + testhashCode());
                    break;
                case 6:
                    inTest = false;
                    break;
                default:
                    System.out.println("Prueba con un numero entre [1..6]");
                    break;
            }
        }

    }
}
