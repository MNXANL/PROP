package tests.domini.PregCualitativaOrdenada;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverPregCualitativaOrdenada {

    private static PregCualitativaOrdenada pco;

    public static void testPregCualitativaOrdenada (String titulo, ArrayList<String> opciones) {
        pco = new PregCualitativaOrdenada(titulo,opciones);
    }

    public static void testTipo () {
        System.out.println(pco.tipo());
    }

    public static void testGetContenido () {
        System.out.println(pco.getContenido());
    }

    public static void testLeer () {
        pco.leer();
    }

    public static void testGetMaxOptions () {
        System.out.println(pco.getMaxOptions());
    }

    public static void testgetPreguntaIesima (int i) {
        System.out.println(pco.getPreguntaIesima(i));
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        ArrayList<String> opciones = new ArrayList<>();
        String o = new String();
        while (!(o = sc.nextLine()).equals("")) {
            opciones.add(o);
        }
        testPregCualitativaOrdenada(titulo,opciones);
        testTipo();
        testGetContenido();
        testLeer();
        testGetMaxOptions();
        int i = sc.nextInt();
        testgetPreguntaIesima(i);
    }
}
