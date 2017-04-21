package tests.domini.RespVacia;


import com.domini.RespLibre;
import com.domini.Respuesta;

import java.util.Scanner;

/**
 * Driver de RespVacia
 */
public class DriverRespVacia {
    private static RespVacia r;


    public static void testDistance(Respuesta s) {
        r = new RespVacia();
        System.out.println(r.distance(s));
    }


    public static void testEquals(Object o) {
        r = new RespVacia();
        System.out.println(r.equals(o));
    }


    public static void testhashCode() {

        System.out.println(r.hashCode());
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Creando respuesta vacía: ");
        RespVacia r = new RespVacia();
        System.out.println("Creada.");

        System.out.println("1. Ver distancia de respuesta vacía respecto respuesta vacía");
        System.out.println("2. Ver distancia de respuesta vacía respecto respuesta NO vacía");
        System.out.println("3. Comprobar que es igual a otra respuesta vacía");
        System.out.println("4. Comprobar que es igual a otra respuesta NO vacía");
        System.out.println("5. Retornar codigo de Hash");
        System.out.println("6. Salir del test");

        boolean inTest = true;
        while (inTest) {
            switch (sc.nextInt()) {
                case 1:
                    Respuesta s = new RespVacia();
                    testDistance(s);
                    break;
                case 2:
                    s = new RespLibre("");
                    testDistance(s);
                    break;
                case 3:
                    s = new RespVacia();
                    testEquals(s);
                    break;
                case 4:
                    s = new RespLibre("");
                    testEquals(s);
                    break;
                case 5:
                    testhashCode();
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
