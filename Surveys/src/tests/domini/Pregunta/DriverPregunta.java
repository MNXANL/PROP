package tests.domini.Pregunta;

import java.util.Scanner;

/**
 * Driver clase pregunta
 */
public class DriverPregunta {

    private static Pregunta p;

    public static void testPregunta () {
        p = new Pregunta();
    }

    public static void testPregunta(String titulo) {
        p = new Pregunta(titulo);
    }

    public static void testgetTitulo () {
        System.out.println(p.getTitulo());
    }

    public static void testsetTitulo (String titulo) {
        p.setTitulo(titulo);
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear pregunta sin título y mostrar título vacío");
        System.out.println("2. Crear pregunta con título y mostrar título");
        System.out.println("3. Crear pregunta sin título, cambiar título y mostrarlo");
        System.out.println("4. Crear pregunta con título, cambiar título y mostrarlo");
        System.out.println("5. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    testPregunta();
                    testgetTitulo();
                    break;
                case 2:
                    testPregunta(sc.nextLine());
                    testgetTitulo();
                    break;
                case 3:
                    testPregunta();
                    testsetTitulo(sc.nextLine());
                    testgetTitulo();
                    break;
                case 4:
                    testPregunta(sc.nextLine());
                    testsetTitulo(sc.nextLine());
                    testgetTitulo();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
