package com.domini;

import java.util.Scanner;

public class Main {

    private static void Presentacion() {
        System.out.println("*** Gestión de encuestas de PROP ***");
        System.out.println("Elige tu opcion:");

        System.out.println("   1. Crear encuesta.");
        System.out.println("   2. Responder encuesta.");
        System.out.println("   3. Visualizar clustering encuesta");
        System.out.println("   4. Ver las respuestas de una encuesta");
        System.out.println("   5. Volver a mostrar este mensaje");
        System.out.println("   6. Salir del programa");
        //System.out.println("   5. Administrar usuarios"); Lo dejamos para el final!
    }

    public static void main(String[] args) {
	// write your code here

        Presentacion();

        Scanner sc = new Scanner(System.in);

        ControladorDominio cd = new ControladorDominio();   //Placeholder controlador capa dominio
        while (true) {
            int option = sc.nextInt();
            switch (option){
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
                    Presentacion();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción invalida. Por favor, vuelve a intentar con un número en el rango [1..4].");
            }
        }
    }
}
