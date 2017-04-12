package com.domini;


/**
 * Clase con pregunta de formato libre
 */
public class PregRespuestaLibre extends Pregunta {
    /**
     * Constructora por defecto
     */
    public PregRespuestaLibre(String titulo) {
        super(titulo);
    }

    public String tipo () {
        return "PRL";
    }

    public void leer () {
        System.out.println(getTitulo());
    }
}
