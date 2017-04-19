package tests.domini.RespCualitativaOrdenada;

import com.domini.RespVacia;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverRespCualitativaOrdenada {

    private static RespCualitativaOrdenada rco;

    public static void testRespCualitativaOrdenada (String text, int num, int nopt) {
        rco = new RespCualitativaOrdenada(num, nopt, text);
    }

    public static void testGet () {
        System.out.println(rco.get());
    }

    public static void testGetText () {
        System.out.println(rco.getText());
    }

    public static void test () {
        rco.equals(rco);
    }

    public static void testGetNOptions () {
        System.out.println(rco.getNoptions());
    }

    public static void testDistance () {
        System.out.println(rco.distance(rco));
    }

    public static void testEquals() {
        System.out.println(rco.equals(rco));
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear respuesta cualitativa ordenada con un título y opciones y mostrar el texto de respuesta");
        System.out.println("2. Crear respuesta cualitativa ordenada con un título y opciones y mostrar el numero de respuesta");
        System.out.println("3. Crear respuesta cualitativa ordenada con un título y opciones y mostrar el numero de opciones de la respuesta");
        System.out.println("4. Crear una respuesta cualitativa ordenada y mostrar distancia respecto sí misma");
        System.out.println("5. Crear respuesta cualitativa ordenada y ver si es igual a sí misma");
        System.out.println("6. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    int n = sc.nextInt();
                    int nopt = sc.nextInt();
                    String titulo = sc.next();
                    testRespCualitativaOrdenada(titulo, n, nopt);
                    testGetText();
                    break;
                case 2:
                    n = sc.nextInt();
                    nopt = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaOrdenada(titulo, n, nopt);
                    testGet();
                    break;
                case 3:
                    n = sc.nextInt();
                    nopt = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaOrdenada(titulo, n, nopt);
                    testGetNOptions();
                    break;
                case 4:
                    n = sc.nextInt();
                    nopt = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaOrdenada(titulo, n, nopt);

                    testDistance();
                    break;
                case 5:
                    n = sc.nextInt();
                    nopt = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaOrdenada(titulo, n, nopt);

                    testEquals();
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
