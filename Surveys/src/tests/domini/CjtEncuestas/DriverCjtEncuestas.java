package tests.domini.CjtEncuestas;

import com.domini.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Driver de la clase CjtEncuestas
 */
public class DriverCjtEncuestas {
    private static CjtEncuestas enc;

    public static void testAddEncuesta(Encuesta e) {
        enc.addEncuesta(e);
    }

    public static void testBorrarEncuesta(String titulo) {
        enc.borrarEncuesta(titulo);
        System.out.println("Encuesta borrada");
    }

    public static void testGetTitulosEncuestas (String criterio) {
        String[] t = enc.getTitulosEncuestas(criterio);
        for (int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
        }
    }

    public static void testGetTitulosEncuestasFecha (Date fecha1, Date fecha2) {
        String[] t = enc.getTitulosEncuestasFecha(fecha1,fecha2);
        for (int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
        }
    }

    public static void testGetTitulosEncuestasPalabras (String palabras) {
        String[] t = enc.getTitulosEncuestasPalabras(palabras);
        for (int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
        }
    }

    public static void testGetEncuesta (String titulo) {
        Encuesta e = enc.getEncuesta(titulo);
        e.leer();
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar");
        System.out.println("1. Añadir una encuesta");
        System.out.println("2. Borrar una encuesta");
        System.out.println("3. Listar las encuestas por un criterio [A-Z, Z-A, nuevas, antiguas]");
        System.out.println("4. Listar las encuestas que se encuentren entre 2 fechas (exclusivo)");
        System.out.println("5. Listar las encuestas que coincidan con la palabra o palabras introducidas");
        System.out.println("6. Fin del test");


        Scanner sc = new Scanner(System.in);

        enc = new CjtEncuestas();

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    System.out.println("Introduce el título de la encuesta");
                    String tituloE = sc.nextLine();
                    Encuesta e = new Encuesta(tituloE);
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
                    testAddEncuesta(e);
                    testGetEncuesta(e.getTitulo());
                    break;
                case 2:
                    System.out.println("Introduce el nombre de la encuesta que quieres borrar");
                    testBorrarEncuesta(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Introduce el criterio [A-Z, Z-A, nuevas, antiguas]");
                    testGetTitulosEncuestas(sc.nextLine());
                    break;
                case 4:
                    try {
                        System.out.println("Introduce la fecha más antigua: [dd/mm/aaaa]");
                        String f1 = sc.nextLine();
                        System.out.println("Introduce la fecha más reciente: [dd/mm/aaaa]");
                        String f2 = sc.nextLine();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha1 = sdf.parse(f1);
                        Date fecha2 = sdf.parse(f2);
                        testGetTitulosEncuestasFecha(fecha1, fecha2);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Introduce el criterio de búsqueda");
                    testGetTitulosEncuestasPalabras(sc.nextLine());
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
