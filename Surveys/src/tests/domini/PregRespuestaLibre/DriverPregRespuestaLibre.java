package tests.domini.PregRespuestaLibre;

import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverPregRespuestaLibre {
    private static PregRespuestaLibre prl;

    public static void testPregRespuestaLibre (String titulo) {
        prl = new PregRespuestaLibre(titulo);
    }

    public static void testTipo () {
        System.out.println(prl.tipo());
    }

    public static void testLeer () {
        prl.leer();
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        testPregRespuestaLibre(titulo);
        testTipo();
        testLeer();
    }
}
