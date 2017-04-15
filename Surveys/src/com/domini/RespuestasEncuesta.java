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
        this.resps = resps;
    }


    public void printarRespuestas() {
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
                RespCualitativaOrdenada rco = (RespCualitativaOrdenada) resps.get(i);
                System.out.println( rco.get() + "/" + rco.getNoptions() );
            }
            else if (resps.get(i) instanceof RespCualitativaNoOrdenadaUnica) {
                RespCualitativaNoOrdenadaUnica rc = (RespCualitativaNoOrdenadaUnica) resps.get(i);
                System.out.println( rc.get() );
            }
            else if (resps.get(i) instanceof RespCualitativaNoOrdenadaMultiple) {
                RespCualitativaNoOrdenadaMultiple rc = (RespCualitativaNoOrdenadaMultiple) resps.get(i);
                Set<Integer> set = rc.get();
                if (set.size() == 1)    System.out.println("1 opcion elegida");
                else                    System.out.println(set.size() + " opciones elegidas");
                for (Integer j : set) {
                    //<Text> es un placeholder y sin tocar nada veo un lio de navegabilidades...
                    System.out.println(" --> " + j + " | " + "<Text>");
                }
            }
        }
    }
}
