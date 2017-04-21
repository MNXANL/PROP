package tests.domini.RespLibre;


import com.domini.RespLibre;
import com.domini.Respuesta;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import tests.domini.RespVacia.RespVacia;

import java.util.Scanner;

/**
 * Driver de RespVacia
 */
public class DriverRespLibre {
    private static RespLibre r;

    public static void testConstructor(String s) {
        r = new RespLibre(s);
        System.out.println("Respuesta: " + r.get());
    }

    public static void testSet(String s) {
        r.set(s);
        System.out.println("Respuesta: " + r.get());
    }

    public static double testDistance(Respuesta rr) {
        return r.distance(r);
    }


    public static boolean testEquals(Object o) {
        return r.equals(o);
    }


    public static int testhashCode() {
        return r.hashCode();
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Crear respuesta sin título");
        System.out.println("2. Crear respuesta con un título");
        System.out.println("3. Comprobar que es igual a otra respuesta libre (previamente definida)");
        System.out.println("4. Comprobar que es igual a otra respuesta NO libre");
        System.out.println("5. Retornar codigo de Hash");
        System.out.println("6. Salir del test");

        boolean inTest = true;
        while (inTest) {
            switch (sc.nextInt()) {
                case 1:
                    testConstructor("");
                    break;
                case 2:
                    String txt = sc.next();
                    testConstructor(txt);
                    break;
                case 3:
                    txt = sc.next();
                    RespLibre rl = new RespLibre(txt);
                    boolean b = testEquals(rl);
                    if (b) System.out.println("TRUE");
                    else   System.out.println("FALSE");
                   break;
                case 4:
                    Respuesta rv = new RespVacia();
                    b = testEquals(rv);
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
