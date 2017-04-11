package com.domini;
import java.io.*;

public class Main {


    private static void Presentacion() {
        System.out.println("*** Gestión de encuestas de PROP ***");
        System.out.println("Elige tu opcion:");

        System.out.println("   1. Crear encuesta.");
        System.out.println("   2. Responder encuesta.");
        System.out.println("   3. Visualizar clustering encuesta");
        System.out.println("   4. Salir del programa");
        //System.out.println("   5. Administrar usuarios"); Lo dejamos para el final!
    }

    public static void main(String[] args) {
	// write your code here
        Encuesta e = new Encuesta();
        e.importar("src/com/domini/Encuesta.txt");
        e.leer();

        Presentacion();

        //CtrlDomini cd = new CtrlDomini();   //Placeholder controlador capa dominio
        while (true) {
            try {
                int option = System.in.read();
                switch (option){
                    case 1:
                        //cd.CreaEncuesta();
                    case 2:
                        //cd.ResponderEncuesta();
                    case 3:
                        //cd.ClusteringKmeans();
                    case 4:
                        break;
                    default:
                        System.out.println("Opción invalida. Por favor, vuelve a intentar con un número en el rango [1..4].");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
