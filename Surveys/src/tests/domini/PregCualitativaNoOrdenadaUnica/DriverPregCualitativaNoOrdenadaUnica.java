package tests.domini.PregCualitativaNoOrdenadaUnica;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver PregCualitativaNoOrdenadaUnica
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
        System.out.print(pcnou.getContenido());
    }

    public static void testLeer () {
        pcnou.leer();
    }

    public static void testgetPreguntaIesima (int i) {
        System.out.println(pcnou.getPreguntaIesima(i));
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear pregunta cualitativa no ordenada única con un título y opciones y mostrar el contenido de la pregunta");
        System.out.println("2. Crear una pregunta cualitativa no ordenada única y mostrar el tipo de pregunta creada");
        System.out.println("3. Crear pregunta cualitativa no ordenada única con un título y opciones y mostrar la opción i-esima");
        System.out.println("4. Crear pregunta cualitativa no ordenada única con un título y opciones y mostrar el contenido sin el título");
        System.out.println("5. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    String titulo = sc.nextLine();
                    ArrayList<String> opciones = new ArrayList<>();
                    String op = new String();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaUnica(titulo,opciones);
                    testLeer();
                    break;
                case 2:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaUnica(titulo,opciones);
                    testTipo();
                    break;
                case 3:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaUnica(titulo,opciones);
                    testgetPreguntaIesima(sc.nextInt());
                    sc.nextLine();
                    break;
                case 4:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaUnica(titulo,opciones);
                    testGetContenido();
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
