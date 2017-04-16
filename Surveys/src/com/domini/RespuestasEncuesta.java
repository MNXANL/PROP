package com.domini;

import java.util.*;

/**
 * Esta clase es el conjunto de respuestas a una encuesta en concreto
 */
public class RespuestasEncuesta {
    ArrayList<Respuesta> resps;
    Encuesta Encuesta_respondida;
    String User;

    /**
     * Creadora
     * @param e (El titulo de la) La encuesta a la que corresponden las preguntas
     * @param user El usuario que ha respondido
     */
    public RespuestasEncuesta(Encuesta e, String user) {
        Encuesta_respondida = e;
        User = user;
        resps = new ArrayList<>();
    }
    public void addRespuesta(Respuesta r){
        resps.add(r);
    }

    /**
     * Creadora
     * @param e (El titulo de la) La encuesta a la que corresponden las preguntas
     * @param user El usuario que ha respondido
     * @param resps Las respuestas de la encuesta en sí
     */
    public RespuestasEncuesta(Encuesta e, String user, ArrayList<Respuesta> resps) {
        Encuesta_respondida = e;
        User = user;
        this.resps = (ArrayList<Respuesta>) resps.clone();
    }


    public void printarRespuestas() {
        System.out.println("Respuestas del usuario " + User);
        System.out.println("----------------------");
        for (int i = 0; i < resps.size(); i++) {
            System.out.print("Respuesta pregunta " + i + ": ");

            if (resps.get(i) instanceof RespLibre) {
                RespLibre rl = (RespLibre) resps.get(i);
                System.out.println( rl.get() );
            }
            else if (resps.get(i) instanceof RespNumerica) {
                RespNumerica rn = (RespNumerica) resps.get(i);
                System.out.println( rn.get() );
            }
            else if (resps.get(i) instanceof RespCualitativaOrdenada) {
                RespCualitativaOrdenada rc = (RespCualitativaOrdenada) resps.get(i);
                System.out.println( rc.getText() + " (" + rc.get() + ")");
            }
            else if (resps.get(i) instanceof RespCualitativaNoOrdenadaUnica) {
                RespCualitativaNoOrdenadaUnica rc = (RespCualitativaNoOrdenadaUnica) resps.get(i);
                System.out.println( rc.getTxt() );
            }

            // !acabame!
            else if (resps.get(i) instanceof RespCualitativaNoOrdenadaMultiple) {
                RespCualitativaNoOrdenadaMultiple rc = (RespCualitativaNoOrdenadaMultiple) resps.get(i);
                Collection<String> colResps = rc.getMap().values();

                if (colResps.size() == 1) System.out.println("1 opción elegida");
                else                 System.out.println(colResps.size() + " opciones elegidas");
                int j = 0;
                for (String col: colResps) {
                    System.out.println(" - Opción " + j + ": " + col);
                    ++j;
                }
            }
        }
        System.out.println();
    }
}
