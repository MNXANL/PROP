package tests.domini.RespCualitativaNoOrdenadaMultiple;

import tests.domini.RespCualitativaNoOrdenadaMultiple.RespCualitativaNoOrdenadaMultiple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by aleixballetbo on 16/4/17.
 */
public class DriverRespCualitativaNoOrdenadaMultiple {

    private static RespCualitativaNoOrdenadaMultiple rco;

    public static void testRespCualitativaNoOrdenadaMultiple (int num, String text) {
        HashMap<Integer,String> hm = new HashMap<Integer,String>();
        hm.put(num, text);
        rco = new RespCualitativaNoOrdenadaMultiple(hm);
    }

    public static void testGet () {
        ArrayList<Integer> i = new ArrayList<Integer> (rco.getMap().keySet());
        System.out.println(i.get(0));
    }

    public static void testGetText () {
        ArrayList<String> t = new ArrayList<>(rco.getMap().values());
        System.out.println(t.get(0));
    }

    public static void testDistance () {
        System.out.println(rco.distance(rco));
    }

    public static void testEquals() {
        System.out.println(rco.equals(rco));
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar:");
        System.out.println("1. Crear respuesta cualitativa no ordenada multiple con un solo numero y título y mostrar el texto de respuesta");
        System.out.println("2. Crear respuesta cualitativa no ordenada multiple con un solo numero y título y mostrar el numero de respuesta");
        System.out.println("3. Crear una respuesta cualitativa no ordenada multiple y mostrar distancia respecto sí misma");
        System.out.println("4. Crear respuesta cualitativa no ordenada multiple y ver si es igual a sí misma");
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
                    testRespCualitativaNoOrdenadaMultiple(n, titulo);
                    testGetText();
                    break;
                case 2:
                    n = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaNoOrdenadaMultiple(n, titulo);
                    testGet();
                    break;
                case 3:
                    n = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaNoOrdenadaMultiple(n, titulo);

                    testDistance();
                    break;
                case 4:
                    n = sc.nextInt();
                    titulo = sc.next();
                    testRespCualitativaNoOrdenadaMultiple(n, titulo);

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
