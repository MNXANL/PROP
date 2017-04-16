package tests.domini.PregCualitativaNoOrdenadaUnica;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverPregCualitativaNoOrdenadaUnica {
    private static PregCualitativaNoOrdenadaUnica pcnou;

    public static void testPregCualitativaNoOrdenadaUnica (String titulo, ArrayList<String> opciones) {
        pcnou = new PregCualitativaNoOrdenadaUnica(titulo,opciones);
    }

    public static void testTipo () {
        System.out.println(pcnou.tipo());
    }

    public static void testGetContenido () {
        System.out.println(pcnou.getContenido());
    }

    public static void testLeer () {
        pcnou.leer();
    }

    public static void testgetPreguntaIesima (int i) {
        System.out.println(pcnou.getPreguntaIesima(i));
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
        int i = sc.nextInt();
        testgetPreguntaIesima(i);
    }
}
