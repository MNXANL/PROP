package com.domini;

/**
 * Excepcion de encuesta existente
 */
public class ExcEncuestaExistente extends Exception{

    /**
     * Constructora basica
     */
    public ExcEncuestaExistente() {
        super();
    }

    /**
     * Constructora con parámetro
     * @param s Texto de la excepcion
     */
    public ExcEncuestaExistente(String s) {
        super(s);
    }
}
