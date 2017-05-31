package com.domini;

/**
 * Excepcion de usuario existente
 */
public class ExcUsuarioExistente extends Exception{
    /**
     * Constructora basica
     */
    public ExcUsuarioExistente() {
        super();
    }

    /**
     * Constructora con parámetro
     * @param s Texto de la excepcion
     */
    public ExcUsuarioExistente(String s) {
        super(s);
    }
}
