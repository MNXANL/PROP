package com.domini;

/**
 * Clase que representa  una respuesta a una pregunta de una encuesta
 */
abstract public class Respuesta {
    /**
     * Distancia entre dos respuestas cualitativas ordenadas
     * @param r respuesta a comparar
     * @return valor entre 0 y 1 que representa la distancia
     */
    abstract public double distance(Respuesta r);

    /**
     * Metodo para comparar la clase con otro objeto
     * @param o Objeto a comparar
     * @return Si los objetos son los mismos o no
     */
    @Override
    abstract public boolean equals(Object o );

    /**
     * Metodo para obtener el codigo de hash
     * @return El codigo de hash
     */
    @Override
    abstract public int hashCode();
}
