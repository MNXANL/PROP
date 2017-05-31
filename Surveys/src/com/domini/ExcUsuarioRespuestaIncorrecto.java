package com.domini;

/**
 * Excepcion de usuario existente
 */
public class ExcUsuarioRespuestaIncorrecto extends Exception {
    /**
     * Constructora basica
     */
    public ExcUsuarioRespuestaIncorrecto() {
        super();
    }

    /**
     * Constructora con parámetro
     * @param s Texto de la excepcion
     */
    public ExcUsuarioRespuestaIncorrecto(String s) {
        super(s);
    }
}
