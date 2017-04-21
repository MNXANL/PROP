package tests.domini.RespuestasEncuesta;

import com.domini.*;
import tests.domini.RespuestasEncuesta.RespuestasEncuesta;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverRespuestasEncuesta {

    private static RespuestasEncuesta re;

    public static void testRespuestasEncuesta (Encuesta e, String s) {
        re = new RespuestasEncuesta(e, s);
    }

    public static void testGetDate () {
        System.out.println("Encuesta '" + re.getEncuesta().getTitulo() + "' creada el dia: " + re.getFecha().toString());
    }

    public static void testGetFichero () {
        System.out.println(re.getNombreFichero());
    }


    public static void testGetHash () {
        System.out.println(re.hashCode());
    }

    public static void testPrint () {
        ArrayList<Respuesta> alr = new ArrayList<>();
        alr.add(new RespLibre("a"));
        alr.add(new RespNumerica(10, 0, 100));
        alr.add(new RespLibre("b"));
        alr.add(new RespCualitativaNoOrdenadaUnica(2,  "Azul"));
        alr.add(new RespCualitativaOrdenada(8, 10, "Notable"));
        re = new RespuestasEncuesta(re.getEncuesta(), re.getUser(), alr);
        re.printarRespuestas();
    }


    public static void main(String[] args) {
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear respuestas de encuesta con un solo titulo de encuesta y mostrar su hashcode");
        System.out.println("2. Crear respuestas de encuesta con un solo titulo de encuesta y ver fecha de creacion");
        System.out.println("3. Crear respuestas de encuesta prefedinida y printar respuestas");
        System.out.println("4. Crear respuestas de encuesta con un solo titulo de encuesta y ver el fichero que se generaria al exportar");
        System.out.println("5. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    System.out.println("Titulo encuesta:");
                    String titulo = sc.next();
                    Encuesta e = new Encuesta(titulo);
                    testRespuestasEncuesta(e, "test");

                    testGetHash();
                    break;
                case 2:
                    System.out.println("Titulo encuesta:");
                    titulo = sc.next();
                    e = new Encuesta(titulo);
                    testRespuestasEncuesta(e, "test");

                    testGetDate();
                    break;
                case 3:
                    System.out.println("Titulo encuesta:");
                    titulo = sc.next();
                    e = new Encuesta("titulo");
                    testRespuestasEncuesta(e, "test");
                    testPrint();
                    break;
                case 4:
                    System.out.println("Titulo encuesta:");
                    titulo = sc.next();
                    e = new Encuesta("titulo");
                    testRespuestasEncuesta(e, "test");

                    testGetFichero();
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
