package com.domini;

import java.util.ArrayList;

/**
 * Esta clase es el conjunto de respuestas a una encuesta en concreto
 */
public class RespuestasEncuesta {
    ArrayList<Respuesta> resps;
    String Encuesta_respondida;
    String User;

    /**
     * Creadora
     * @param title El titulo de la encuesta a la que corresponden las preguntas
     * @param user EL usuario que ha respondido
     */
    public RespuestasEncuesta(String title, String user) {
        Encuesta_respondida=title;
        User=user;
    }

    public void ResponderPreguntasDeUnaEncuesta() {
        Encuesta e = new Encuesta(Encuesta_respondida);
        e.responder(this);
        //...

    }
}
