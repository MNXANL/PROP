package tests.domini.PregCualitativaNoOrdenadaMultiple;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverPregCualitativaNoOrdenadaMultiple {
    private static PregCualitativaNoOrdenadaMultiple pcnom;

    public static void testPregCualitativaNoOrdenadaUnica (String titulo, ArrayList<String> opciones) {
        pcnom = new PregCualitativaNoOrdenadaMultiple(titulo,opciones);
    }

    public static void testPregCualitativaNoOrdenadaUnica (String titulo, ArrayList<String> opciones, int maxOpciones) {
        pcnom = new PregCualitativaNoOrdenadaMultiple(titulo,opciones,maxOpciones);
    }

    public static void testTipo () {
        System.out.println(pcnom.tipo());
    }

    public static void testGetContenido () {
        System.out.println(pcnom.getContenido());
    }

    public static void testLeer () {
        pcnom.leer();
    }

    public static void testGetMaxOptions () {
        System.out.println(pcnom.getMaxOptions());
    }

    public static void testgetPreguntaIesima (int i) {
        System.out.println(pcnom.getPreguntaIesima(i));
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        ArrayList<String> opciones = new ArrayList<>();
        String o = new String();
        while (!(o = sc.nextLine()).equals("")) {
            opciones.add(o);
        }
        testPregCualitativaNoOrdenadaUnica(titulo,opciones);
        testTipo();
        testGetContenido();
        testLeer();
        testGetMaxOptions();
        int i = sc.nextInt();
        testgetPreguntaIesima(i);
    }
}
