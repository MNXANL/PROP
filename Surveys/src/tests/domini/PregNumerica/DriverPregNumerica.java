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
    }

    public static void testSetValorMax (float valorMax) {
        pn.setValorMax(valorMax);
    }

    public static void testTipo () {
        System.out.println(pn.tipo());
    }

    public static void testGetContenido () {
        System.out.print(pn.getContenido());
    }

    public static void testLeer () {
        pn.leer();
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear pregunta numérica sin límite en los valores y mostrar contenido de la pregunta");
        System.out.println("2. Crear pregunta numérica con límite en los valores y mostrar contenido de la pregunta");
        System.out.println("3. Crear pregunta numérica con límite en los valores, cambiar dichos valores y mostrarlos");
        System.out.println("4. Crear una pregunta numérica y mostrar el tipo de la pregunta creada");
        System.out.println("5. Crear una pregunta numérica y mostrar el contenido sin el título");
        System.out.println("6. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    testPregNumerica(sc.nextLine());
                    testLeer();
                    break;
                case 2:
                    testPregNumerica(sc.nextLine(),sc.nextFloat(),sc.nextFloat());
                    testLeer();
                    sc.nextLine();
                    break;
                case 3:
                    testPregNumerica(sc.nextLine(),sc.nextFloat(),sc.nextFloat());
                    testSetValorMin(sc.nextFloat());
                    testSetValorMax(sc.nextFloat());
                    testGetValorMin();
                    testGetValorMax();
                    sc.nextLine();
                    break;
                case 4:
                    testPregNumerica(sc.nextLine(), sc.nextFloat(), sc.nextFloat());
                    sc.nextLine();
                    testTipo();
                    break;
                case 5:
                    testPregNumerica(sc.nextLine(), sc.nextFloat(), sc.nextFloat());
                    testGetContenido();
                    sc.nextLine();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
