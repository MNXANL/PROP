package tests.domini.RespNumerica;


import com.domini.RespNumerica;
import com.domini.Respuesta;
import tests.domini.RespVacia.RespVacia;

import java.util.Scanner;

/**
 * Driver de RespVacia
 */
public class DriverRespNumerica {
    private RespNumerica r;

    public void testConstructor(double x, double min, double max) {
        r = new RespNumerica(x, min, max);
        System.out.println("Respuesta: " + r.get());
    }

    public void testMin(double x, double min, double max) {
        r = new RespNumerica(x, min, max);
        System.out.println("Respuesta: " + r.getMin());
    }

    public void testMax(double x, double min, double max) {
        r = new RespNumerica(x, min, max);
        System.out.println("Respuesta: " + r.getMax());
    }

    public void testSet(double s) {
        r = new RespNumerica(0, Double.MIN_VALUE, Double.MAX_VALUE);
        r.set(s);
        System.out.println("Respuesta: " + r.get());
    }

    public double testDistance(Respuesta rr) {
        return r.distance(rr);
    }


    public boolean testEquals(Object o) {
        return r.equals(o);
    }


    public int testhashCode() {
        return r.hashCode();
    }

    public void main () {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Crear respuesta sin valores");
        System.out.println("2. Crear respuesta con un valor, maximo y minimo");
        System.out.println("3. Crear respuesta y devolver valor minimo");
        System.out.println("4. Crear respuesta y devolver valor maximo");
        System.out.println("5. Retornar codigo de Hash");
        System.out.println("6. Salir del test");

        boolean inTest = true;
        while (inTest) {
            switch (sc.nextInt()) {
                case 1:
                    testConstructor(0, Double.MIN_VALUE, Double.MAX_VALUE);
                    break;
                case 2:
                    double x = sc.nextDouble();
                    double min = sc.nextDouble();
                    double max = sc.nextDouble();
                    testConstructor(x, min, max);
                    break;
                case 3:
                    x = sc.nextDouble();
                    min = sc.nextDouble();
                    max = sc.nextDouble();
                    testMax(x, min, max);
                   break;
                case 4:
                    x = sc.nextDouble();
                    min = sc.nextDouble();
                    max = sc.nextDouble();
                    testMin(x, min, max);
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
