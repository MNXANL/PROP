package com.domini;

/**
 * Excepcion de formato incorrecto
 */
public class ExcFormatoIncorrecto extends Exception {

    /**
     * Constructora basica
     */
    public ExcFormatoIncorrecto() {
        super();
    }

    /**
     * Constructora con parámetro
     * @param s Texto de la excepcion
     */
    public ExcFormatoIncorrecto(String s) {
        super(s);
    }
}
