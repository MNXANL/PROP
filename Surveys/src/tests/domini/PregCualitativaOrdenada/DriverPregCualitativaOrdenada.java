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
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear pregunta cualitativa ordenada con un título y opciones y mostrar el contenido de la pregunta");
        System.out.println("2. Crear pregunta cualitativa ordenada con un título y opciones y mostrar el numero de opciones disponibles");
        System.out.println("3. Crear una pregunta cualitativa ordenada y mostrar el tipo de pregunta creada");
        System.out.println("4. Crear pregunta cualitativa ordenada con un título y opciones y mostrar la opción i-esima");
        System.out.println("5. Crear pregunta cualitativa ordenada con un título y opciones y mostrar el contenido sin el título");
        System.out.println("6. Fin del test");

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
                    testPregCualitativaOrdenada(titulo,opciones);
                    testLeer();
                    break;
                case 2:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaOrdenada(titulo,opciones);
                    testGetMaxOptions();
                    break;
                case 3:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaOrdenada(titulo,opciones);
                    testTipo();
                    break;
                case 4:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaOrdenada(titulo,opciones);
                    testgetPreguntaIesima(sc.nextInt());
                    sc.nextLine();
                    break;
                case 5:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaOrdenada(titulo,opciones);
                    testGetContenido();
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
