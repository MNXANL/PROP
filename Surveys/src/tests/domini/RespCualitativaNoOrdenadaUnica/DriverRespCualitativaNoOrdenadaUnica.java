package tests.domini.RespCualitativaNoOrdenadaUnica;

import tests.domini.RespCualitativaNoOrdenadaUnica.RespCualitativaNoOrdenadaUnica;

import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverRespCualitativaNoOrdenadaUnica {

    private static RespCualitativaNoOrdenadaUnica rco;

    public static void testRespCualitativaNoOrdenadaUnica (int num, String text) {
        rco = new RespCualitativaNoOrdenadaUnica(num, text);
    }

    public static void testGet () {
        System.out.println(rco.get());
    }

    public static void testGetText () {
        System.out.println(rco.getText());
    }

    public static void testDistance () {
        System.out.println(rco.distance(rco));
    }

    public static void testEquals() {
        System.out.println(rco.equals(rco));
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear respuesta cualitativa no ordenada unica con un numero y título y mostrar el texto de respuesta");
        System.out.println("2. Crear respuesta cualitativa no ordenada unica con un numero y título y mostrar el numero de respuesta");
        System.out.println("3. Crear una respuesta cualitativa no ordenada unica y mostrar distancia respecto sí misma");
        System.out.println("4. Crear respuesta cualitativa no ordenada unica y ver si es igual a sí misma");
        System.out.println("5. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    int n = sc.nextInt();
                    String titulo = sc.next();
                    testRespCualitativaNoOrdenadaUnica(n, titulo);
                    testGetText();
                    break;
                case 2:
                    n = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaNoOrdenadaUnica(n, titulo);
                    testGet();
                    break;
                case 3:
                    n = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaNoOrdenadaUnica(n, titulo);

                    testDistance();
                    break;
                case 4:
                    n = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaNoOrdenadaUnica(n, titulo);

                    testEquals();
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
