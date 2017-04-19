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
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear pregunta de respuesta libre con un título y mostrar el contenido de la pregunta");
        System.out.println("2. Crear una pregunta de respuesta libre y mostrar el tipo de pregunta creada");
        System.out.println("3. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    testPregRespuestaLibre(sc.nextLine());
                    testLeer();
                    break;
                case 2:
                    testPregRespuestaLibre(sc.nextLine());
                    testTipo();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
