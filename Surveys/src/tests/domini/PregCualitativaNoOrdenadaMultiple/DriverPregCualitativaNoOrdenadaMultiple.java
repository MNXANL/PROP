package tests.domini.PregCualitativaNoOrdenadaMultiple;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverPregCualitativaNoOrdenadaMultiple {
    private static PregCualitativaNoOrdenadaMultiple pcnom;

    public static void testPregCualitativaNoOrdenadaMultiple (String titulo, ArrayList<String> opciones) {
        pcnom = new PregCualitativaNoOrdenadaMultiple(titulo,opciones);
    }

    public static void testPregCualitativaNoOrdenadaMultiple (String titulo, ArrayList<String> opciones, int maxOpciones) {
        pcnom = new PregCualitativaNoOrdenadaMultiple(titulo,opciones,maxOpciones);
    }

    public static void testTipo () {
        System.out.println(pcnom.tipo());
    }

    public static void testGetContenido () {
        System.out.print(pcnom.getContenido());
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
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear pregunta cualitativa no ordenada múltiple con un título, opciones y sin número máximo de respuestas y mostrar el contenido de la pregunta");
        System.out.println("2. Crear pregunta cualitativa ordenada con un título, opciones y número máximo de respuestas y mostrar el contenido de la pregunta");
        System.out.println("3. Crear pregunta cualitativa ordenada con un título, opciones y número máximo de respuestas y mostrar el número máximo de respuestas");
        System.out.println("4. Crear una pregunta cualitativa no ordenada múltiple con un título, opciones y número máximo de respuestas y mostrar el tipo de pregunta creada");
        System.out.println("5. Crear pregunta cualitativa no ordenada mútliple con un título, opciones y número máximo de respuestas y mostrar la opción i-esima");
        System.out.println("6. Crear pregunta cualitativa no ordenada múltiple con un título, opciones y número máximo de respuestas y mostrar el contenido sin el título");
        System.out.println("7. Fin del test");

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
                    testPregCualitativaNoOrdenadaMultiple(titulo,opciones);
                    testLeer();
                    break;
                case 2:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaMultiple(titulo,opciones,sc.nextInt());
                    testLeer();
                    sc.nextLine();
                    break;
                case 3:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaMultiple(titulo,opciones,sc.nextInt());
                    testGetMaxOptions();
                    sc.nextLine();
                case 4:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaMultiple(titulo,opciones,sc.nextInt());
                    testTipo();
                    sc.nextLine();
                    break;
                case 5:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaMultiple(titulo,opciones, sc.nextInt());
                    testgetPreguntaIesima(sc.nextInt());
                    sc.nextLine();
                    break;
                case 6:
                    titulo = sc.nextLine();
                    opciones = new ArrayList<>();
                    while (!(op = sc.nextLine()).equals("")) {
                        opciones.add(op);
                    }
                    testPregCualitativaNoOrdenadaMultiple(titulo,opciones, sc.nextInt());
                    testGetContenido();
                    sc.nextLine();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
