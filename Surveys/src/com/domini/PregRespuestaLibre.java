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

    public void PosiblesRespuestas(){
        System.out.println("Formato libre.");
    }
}
