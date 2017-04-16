package tests.domini.PregNumerica;

import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverPregNumerica {
    private static PregNumerica pn;

    public static void testPregNumerica (String titulo) {
        pn = new PregNumerica(titulo);
    }

    public static void testPregNumerica (String titulo, float valorMin, float valorMax) {
        pn = new PregNumerica(titulo,valorMin,valorMax);
    }

    public static void testGetValorMin () {
        System.out.println(pn.getValorMin());
    }

    public static void testGetValorMax() {
        System.out.println(pn.getValorMax());
    }

    public static void testSetValorMin (float valorMin) {
        pn.setValorMin(valorMin);
        testGetValorMin();
    }

    public static void testSetValorMax (float valorMax) {
        pn.setValorMax(valorMax);
        testGetValorMax();
    }

    public static void testTipo () {
        System.out.println(pn.tipo());
    }

    public static void testGetContenido () {
        System.out.println(pn.getContenido());
    }

    public static void testLeer () {
        pn.leer();
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        float min = sc.nextFloat();
        float max = sc.nextFloat();
        testPregNumerica(titulo,min,max);
        float newMin = sc.nextFloat();
        testSetValorMin(newMin);
        float newMax = sc.nextFloat();
        testSetValorMax(newMax);
        testTipo();
        testGetContenido();
        testLeer();
    }
}
