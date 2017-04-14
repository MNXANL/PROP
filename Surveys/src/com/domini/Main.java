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
        System.out.println("   8. Salir del programa");
        //System.out.println("   5. Administrar usuarios"); Lo dejamos para el final!
    }

    public static void main(String[] args) {
	// write your code here

        Presentacion();

        Scanner sc = new Scanner(System.in);

        ControladorDominio cd = new ControladorDominio();

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
                    cd.responderEncuesta();
                    break;
                case 3:
                    //cd.ClusteringKmeans();
                    break;
                case 4:
                    cd.verRespuestasEncuesta();
                    break;
                case 5:
                    System.out.println("Introduce el criterio de búsqueda:");
                    sc.nextLine();
                    String criterio = sc.nextLine();
                    String[] titulos = cd.listaEncuestas(criterio);
                    for (int i = 0; i < titulos.length; i++) {
                        System.out.println(titulos[i]);
                    }
                    break;
                case 6:
                    System.out.println("Encuestas disponibles:");
                    String[] t = cd.listaEncuestas("A-Z");
                    for (int i = 0; i < t.length; i++) {
                        System.out.println(t[i]);
                    }
                    System.out.println("Escribe el nombre de la encuesta que quieres modificar");
                    sc.nextLine();
                    cd.modificarEncuesta(sc.nextLine());
                    break;
                case 7:
                    System.out.println("Encuestas disponibles:");
                    String[] ti = cd.listaEncuestas("A-Z");
                    for (int i = 0; i < ti.length; i++) {
                        System.out.println(ti[i]);
                    }
                    System.out.println("Escribe el nombre de la encuesta que quieres borrar");
                    sc.nextLine();
                    cd.borrarEncuesta(sc.nextLine());
                    break;
                case 8:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción invalida. Por favor, vuelve a intentar con un número en el rango [1..6].");
            }
        }
    }
}
