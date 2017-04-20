package tests.domini.Administrador;

import java.util.Scanner;

/**
 * Driver de la clase Administrador
 */
public class DriverAdministrador {
    private static Administrador a;

    public static void testAdministrador (String n) {
        a = new Administrador(n);
        a.leer();
    }


    public static void main (String [] args) {
        System.out.println("Elige la opción a probar");
        System.out.println("1. Crear administrador con un nombre y mostrar nombre");
        System.out.println("2. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    testAdministrador(sc.nextLine());
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
