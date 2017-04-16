package com.domini;

import java.util.Scanner;

public class Main {

    private static void Presentacion() {
        System.out.println("*** Gestión de encuestas de PROP ***");
        System.out.println("Elige tu opcion:");

        System.out.println("   0. Menú");
        System.out.println("   1. Crear encuesta.");
        System.out.println("   2. Responder encuesta.");
        System.out.println("   3. Visualizar clustering encuesta");
        System.out.println("   4. Ver las respuestas de una encuesta");
        System.out.println("   5. Lista de las encuestas");
        System.out.println("   6. Modificar encuesta");
        System.out.println("   7. Borrar encuesta");
        System.out.println("   8. Iniciar sesion");
        System.out.println("   9. Cerrar sesion");
        System.out.println("   10. Crear nuevo usuario");
        System.out.println("   11. Salir del programa");
    }

    public static void main(String[] args) {
        ControladorDominio cd = new ControladorDominio();

        Presentacion();

        Scanner sc = new Scanner(System.in);


        boolean continuar = true;
        while (continuar) {
            int option = sc.nextInt();
            switch (option){
                case 0:
                    Presentacion();
                    break;
                case 1:
                    cd.crearEncuesta();
                    break;
                case 2:
                    System.out.println("Escribe el nombre de la encuesta que quieres responder: ");
                    sc.nextLine();
                    cd.responderEncuesta(sc.nextLine());
                    break;
                case 3:
                    //cd.ClusteringKmeans();
                    break;
                case 4:
                    System.out.println("Escribe el nombre de la encuesta para mostrar sus respuestas: ");
                    sc.nextLine();
                    cd.verRespuestasEncuesta(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Introduce el criterio de búsqueda:");
                    sc.nextLine();
                    String criterio = sc.nextLine();
                    String[] titulos = cd.listaEncuestas(criterio);
                    for (String titulo : titulos) {
                        System.out.println(titulo);
                    }
                    break;
                case 6:
                    System.out.println("Encuestas disponibles:");
                    String[] t = cd.listaEncuestas("A-Z");
                    for (String i : t) {
                        System.out.println(i);
                    }
                    System.out.println("Escribe el nombre de la encuesta que quieres modificar");
                    sc.nextLine();
                    cd.modificarEncuesta(sc.nextLine());
                    break;
                case 7:
                    System.out.println("Encuestas disponibles:");
                    String[] ti = cd.listaEncuestas("A-Z");
                    for (String i : ti) {
                        System.out.println(i);
                    }
                    System.out.println("Escribe el nombre de la encuesta que quieres borrar");
                    sc.nextLine();
                    cd.borrarEncuesta(sc.nextLine());
                    break;
                case 8:
                    sc.nextLine();
                    System.out.println("Introduce el nombre de usuario");
                    String user = sc.nextLine();
                    System.out.println("Introduce la contraseña");
                    String pass = sc.nextLine();
                    cd.logIn(user,pass);
                    break;
                case 9:
                    cd.logOut();
                    System.out.println("Sesión cerrada");
                    break;
                case 10:
                    sc.nextLine();
                    System.out.println("Qué tipo de usuario eres? [Admin/Enc]");
                    String tU = sc.nextLine();
                    System.out.println("Introduce un nombre de usuario");
                    String u = sc.nextLine();
                    System.out.println("Introduce una contraseña");
                    String p = sc.nextLine();
                    cd.nuevoUsuario(tU, u, p);
                    break;
                case 11:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción invalida. Por favor, vuelve a intentar con un número en el rango [0..11].");
            }
        }
    }
}
