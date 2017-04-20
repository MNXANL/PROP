package tests.domini.Encuestado;

import java.util.Scanner;

/**
 * Created by aleixballetbo on 20/4/17.
 */
public class DriverEncuestado {
    private static Encuestado a;

    public static void testEncuestado (String n) {
        a = new Encuestado(n);
        a.leer();
    }


    public static void main (String [] args) {
        System.out.println("Elige la opción a probar");
        System.out.println("1. Crear encuestado con un nombre y mostrar nombre");
        System.out.println("2. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    testEncuestado(sc.nextLine());
                    break;
                case 2:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
