package tests.domini.Encuesta;

import com.domini.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Driver de la clase Encuesta
 */
public class DriverEncuesta {
    private static Encuesta e = null;

    private static void testSetTitulo(String titulo) {
        e.setTitulo(titulo);
    }

    private static String testGetTitulo() {
        return e.getTitulo();
    }

    private static Date testGetFecha() {
        return e.getFecha();
    }

    private static Pregunta testGetPregunta(int i) {
        return e.getPregunta(i);
    }

    private static void testAddQuestion(Pregunta p) {
        e.add_question(p);
    }

    private static Encuesta testImportar(String path) {
        return Encuesta.importar(path);
    }

    private static void testExportar(String path) {
        e.exportar(path);
    }

    private static void testResponderEncuesta() {
        ArrayList <Respuesta> resp = e.responderEncuesta("test");
        com.domini.Encuesta e_aux = new com.domini.Encuesta(e.getTitulo(), e.getFecha(), e.getPreguntas(), e.getCjtRespsEnc());
        RespuestasEncuesta re =  new RespuestasEncuesta(e_aux, "test", resp);
        e.responder(re);
    }

    private static void testVerRespuestasEncuesta() {
        e.printarRespuestasDeEncuesta();
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar");
        System.out.println("1. Crear encuesta de forma interactiva y mostrarla por pantalla");
        System.out.println("2. Obtener el título de una encuesta previamente creada con la opción 1");
        System.out.println("3. Cambiar el título a una encuesta previamente creada con la opción 1");
        System.out.println("4. Obtener la fecha una encuesta previamente creada con la opción 1");
        System.out.println("5. Obtener la pregunta i-esima de la encuesta creada con la opción 1");
        System.out.println("6. Añadir una pregunta a una encuesta previamente creada con la opción 1 y mostrar la encuesta entera por pantalla");
        System.out.println("7. Importar una encuesta existente");
        System.out.println("8. Exportar una encuesta existente");
        System.out.println("9. Responder una encuesta creada con la opción 1");
        System.out.println("10. Ver las respuestas hechas a una encuesta existente");
        System.out.println("11. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    System.out.println("Introduce el título de la encuesta");
                    String tituloE = sc.nextLine();
                    e = new Encuesta(tituloE);
                    System.out.println("Quieres añadir una nueva pregunta? [sí/no]");
                    String r = sc.nextLine();
                    while (r.toLowerCase().equals("sí") || r.toLowerCase().equals("si")){
                        System.out.println("Introduce el título de la pregunta");
                        String tituloP = sc.nextLine();
                        System.out.println("Introduce el tipo de pregunta [PCO, PCNOU, PCNOM, PN, PRL]");
                        String tipo = sc.nextLine();

                        if (tipo.toUpperCase().equals("PCO")) {
                            System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                            int n = sc.nextInt();
                            sc.nextLine();
                            ArrayList<String> opciones = new ArrayList<>();
                            for (int i = 0; i < n; i++) {
                                System.out.println("Introduce la opción número "+ (i+1));
                                opciones.add(i,sc.nextLine());
                            }
                            PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP,opciones);
                            e.add_question(preg);
                        }
                        else if (tipo.toUpperCase().equals("PCNOU")) {
                            System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                            int n = sc.nextInt();
                            sc.nextLine();
                            ArrayList<String> opciones = new ArrayList<>();
                            for (int i = 0; i < n; i++) {
                                System.out.println("Introduce la opción número "+ (i+1));
                                opciones.add(i,sc.nextLine());
                            }
                            PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP,opciones);
                            e.add_question(preg);
                        }
                        else if (tipo.toUpperCase().equals("PCNOM")) {
                            System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                            int n = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Cuántas opciones se pueden seleccionar a la vez como máximo?");
                            int max = sc.nextInt();
                            sc.nextLine();
                            ArrayList<String> opciones = new ArrayList<>();
                            for (int i = 0; i < n; i++) {
                                System.out.println("Introduce la opción número "+ (i+1));
                                opciones.add(i,sc.nextLine());
                            }
                            PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP,opciones,max);
                            e.add_question(preg);
                        }
                        else if (tipo.toUpperCase().equals("PN")) {
                            System.out.println("Cuál es el valor mínimo que admite ésta pregunta?");
                            int min = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Cuál es el valor máximo que admite ésta pregunta?");
                            int max = sc.nextInt();
                            sc.nextLine();
                            PregNumerica preg = new PregNumerica(tituloP,min,max);
                            e.add_question(preg);
                        }
                        else if (tipo.toUpperCase().equals("PRL")) {
                            PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                            e.add_question(preg);
                        }
                        else {
                            System.out.println("Tipo de pregunta inválido.");
                        }
                        System.out.println("Quieres añadir una nueva pregunta? [sí/no]");
                        r = sc.nextLine();
                    }
                    e.leer();
                    break;
                case 2:
                    if (e != null) {
                        System.out.println(testGetTitulo());
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 3:
                    if (e != null) {
                        System.out.println("Introduce el nuevo título para la encuesta");
                        testSetTitulo(sc.nextLine());
                        System.out.println(testGetTitulo());
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 4:
                    if (e != null) {
                        System.out.println(testGetFecha());
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 5:
                    if (e != null) {
                        System.out.println("Introduce el índice de la pregunta (empezando por 0)");
                        Pregunta p = testGetPregunta(sc.nextInt());
                        p.leer();
                        sc.nextLine();
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 6:
                    if (e != null) {
                        System.out.println("Introduce el título de la pregunta");
                        String tituloP = sc.nextLine();
                        System.out.println("Introduce el tipo de pregunta [PCO, PCNOU, PCNOM, PN, PRL]");
                        String tipo = sc.nextLine();

                        if (tipo.toUpperCase().equals("PCO")) {
                            System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                            int n = sc.nextInt();
                            sc.nextLine();
                            ArrayList<String> opciones = new ArrayList<>();
                            for (int i = 0; i < n; i++) {
                                System.out.println("Introduce la opción número "+ (i+1));
                                opciones.add(i,sc.nextLine());
                            }
                            PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP,opciones);
                            testAddQuestion(preg);
                        }
                        else if (tipo.toUpperCase().equals("PCNOU")) {
                            System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                            int n = sc.nextInt();
                            sc.nextLine();
                            ArrayList<String> opciones = new ArrayList<>();
                            for (int i = 0; i < n; i++) {
                                System.out.println("Introduce la opción número "+ (i+1));
                                opciones.add(i,sc.nextLine());
                            }
                            PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP,opciones);
                            testAddQuestion(preg);
                        }
                        else if (tipo.toUpperCase().equals("PCNOM")) {
                            System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                            int n = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Cuántas opciones se pueden seleccionar a la vez como máximo?");
                            int max = sc.nextInt();
                            sc.nextLine();
                            ArrayList<String> opciones = new ArrayList<>();
                            for (int i = 0; i < n; i++) {
                                System.out.println("Introduce la opción número "+ (i+1));
                                opciones.add(i,sc.nextLine());
                            }
                            PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP,opciones,max);
                            testAddQuestion(preg);
                        }
                        else if (tipo.toUpperCase().equals("PN")) {
                            System.out.println("Cuál es el valor mínimo que admite ésta pregunta?");
                            int min = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Cuál es el valor máximo que admite ésta pregunta?");
                            int max = sc.nextInt();
                            sc.nextLine();
                            PregNumerica preg = new PregNumerica(tituloP,min,max);
                            testAddQuestion(preg);
                        }
                        else if (tipo.toUpperCase().equals("PRL")) {
                            PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                            testAddQuestion(preg);
                        }
                        else {
                            System.out.println("Tipo de pregunta inválido.");
                        }
                        e.leer();
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 7:
                    System.out.println("Introduce el path");
                    e = testImportar(sc.nextLine());
                    e.leer();
                    break;
                case 8:
                    if (e != null) {
                        System.out.println("Introduce el path");
                        testExportar(sc.nextLine());
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 9:
                    if (e != null) {
                        testResponderEncuesta();
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 10:
                    if (e != null) {
                        testVerRespuestasEncuesta();
                    }
                    else if (e != null && e.getCjtRespsEnc().isEmpty()) {
                        System.out.println("Introduce tus respuestas primero con la opción 9");
                    }
                    else {
                        System.out.println("Introduce una encuesta primero con la opción 1");
                    }
                    break;
                case 11:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
