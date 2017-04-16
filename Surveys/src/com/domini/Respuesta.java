package com.domini;

/**
 * Clase que representa  una respuesta a una pregunta de una encuesta
 */
abstract public class Respuesta {
    abstract double distance(Respuesta r);
    @Override
    abstract public boolean equals(Object o );
    @Override
    abstract public int hashCode();
}
