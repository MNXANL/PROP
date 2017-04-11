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
        boolean exit = false;
        //CtrlDomini cd = new CtrlDomini();   //Placeholder controlador capa dominio
        while (!exit) {
            try {
                int option = System.in.read() - '0';
                System.out.println(option);
                if (option == 1) {
                    //cd.CreaEncuesta();
                }
                else if (option == 2) {
                    //cd.RespondeEncuesta();
                }
                else if (option == 3) {
                    //cd.ClusterEncuesta();
                }
                else if (option == 4) {
                    System.out.println(option);
                    exit = true;
                }
                else {
                    System.out.println("Opción invalida. Por favor, vuelve a intentar con un número en el rango [1..4].");
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
