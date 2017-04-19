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
    private RespLibre r;

    public void testConstructor(String s) {
        r = new RespLibre(s);
        System.out.println("Respuesta: " + r.get());
    }

    public void testSet(String s) {
        r.set(s);
        System.out.println("Respuesta: " + r.get());
    }

    public double testDistance(Respuesta rr) {
        return r.distance(r);
    }


    public boolean testEquals(Object o) {
        return r.equals(o);
    }


    public int testhashCode() {
        return r.hashCode();
    }

    public void main () {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Crear respuesta sin título");
        System.out.println("2. Crear respuesta con un título");
        System.out.println("3. Comprobar que es igual a otra respuesta libre");
        System.out.println("3. Comprobar que es igual a otra respuesta NO libre");
        System.out.println("4. Retornar codigo de Hash");
        System.out.println("5. Salir del test");

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
                   break;
                case 4:
                    Respuesta s4 = new RespVacia();
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
