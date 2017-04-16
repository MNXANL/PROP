package tests.domini.Pregunta;

import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverPregunta {

    private static Pregunta p;

    public static void testPregunta () {
        p = new Pregunta();
    }

    public static void testPregunta(String titulo) {
        p = new Pregunta(titulo);
    }

    public static void getTitulo () {
        System.out.println(p.getTitulo());
    }

    public static void setTitulo (String titulo) {
        p.setTitulo(titulo);
        getTitulo();
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        testPregunta(sc.nextLine());
        getTitulo();
        setTitulo(sc.nextLine());
    }
}
