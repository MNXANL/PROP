package com.domini;

import java.util.ArrayList;

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
     * @param user EL usuario que ha respondido
     */
    public RespuestasEncuesta(Encuesta e, String user) {
        Encuesta_respondida = e;
        User=user;
    }

    public void ResponderPreguntasDeUnaEncuesta() {
        resps = Encuesta_respondida.ResponderEncuesta();
    }

    public void printarRespuestas() {
        for (int i = 0; i < resps.size(); i++) {
            RespLibre rl = (RespLibre) resps.get(i);
            System.out.println("Respuesta pregunta " + i + ": " + rl.get());
        }
    }
}
