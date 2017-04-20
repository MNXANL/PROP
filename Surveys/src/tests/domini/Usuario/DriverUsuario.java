package tests.domini.Usuario;

import java.util.Scanner;

/**
 * Created by aleixballetbo on 20/4/17.
 */
public class DriverUsuario {
    private static Usuario u;

    public static void testUsuario(String name) {
        u = new Usuario(name);
    }

    public static void testgetUsername() {
        System.out.println(u.getUsername());
    }

    public static void main (String [] args) {
        System.out.println("Elige la opción a probar");
        System.out.println("1. Crear usuario con un nombre y mostrar nombre");
        System.out.println("2. Fin del test");

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            int o = sc.nextInt();
            sc.nextLine();
            switch (o) {
                case 1:
                    testUsuario(sc.nextLine());
                    testgetUsername();
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
