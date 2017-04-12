package com.domini;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controlador de la capa de dominio
 */
public class ControladorDominio {

    public void crearEncuesta () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el título de la encuesta");
        String tituloE = sc.nextLine();
        Encuesta e = new Encuesta(tituloE);
        System.out.println("Quieres añadir una nueva pregunta? [sí/no]");
        String r = sc.nextLine();
        while (r.equals("sí")){
            System.out.println("Introduce el título de la pregunta");
            String tituloP = sc.nextLine();
            System.out.println("Introduce el tipo de pregunta [PCO, PCNOU, PCNOM, PN, PRL]");
            String tipo = sc.nextLine();
            if (tipo.equals("PCO")) {
                System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                int n = sc.nextInt();
                sc.nextLine();
                ArrayList<String> opciones = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Introduce la opción número "+ (i+1));
                    opciones.add(i,sc.nextLine());
                }
                PregCualitativaOrdenada preg = new PregCualitativaOrdenada(tituloP,opciones);
                e.add_question(preg);
            }
            else if (tipo.equals("PCNOU")) {
                System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                int n = sc.nextInt();
                sc.nextLine();
                ArrayList<String> opciones = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Introduce la opción número "+ (i+1));
                    opciones.add(i,sc.nextLine());
                }
                PregCualitativaNoOrdenadaUnica preg = new PregCualitativaNoOrdenadaUnica(tituloP,opciones);
                e.add_question(preg);
            }
            else if (tipo.equals("PCNOM")) {
                System.out.println("Cuántas opciones de respuesta tiene la pregunta?");
                int n = sc.nextInt();
                sc.nextLine();
                System.out.println("Cuántas opciones se pueden seleccionar a la vez como máximo?");
                int max = sc.nextInt();
                sc.nextLine();
                ArrayList<String> opciones = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Introduce la opción número "+ (i+1));
                    opciones.add(i,sc.nextLine());
                }
                PregCualitativaNoOrdenadaMultiple preg = new PregCualitativaNoOrdenadaMultiple(tituloP,opciones,max);
                e.add_question(preg);
            }
            else if (tipo.equals("PN")) {
                System.out.println("Cuál es el valor mínimo que admite ésta pregunta?");
                int min = sc.nextInt();
                sc.nextLine();
                System.out.println("Cuál es el valor máximo que admite ésta pregunta?");
                int max = sc.nextInt();
                sc.nextLine();
                PregNumerica preg = new PregNumerica(tituloP,min,max);
                e.add_question(preg);
            }
            else if (tipo.equals("PRL")) {
                PregRespuestaLibre preg = new PregRespuestaLibre(tituloP);
                e.add_question(preg);
            }
            System.out.println("Quieres añadir una nueva pregunta? [sí/no]");
            r = sc.nextLine();
        }
        e.leer();
    }

}
