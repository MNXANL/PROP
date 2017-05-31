package com.domini;

/**
 * Clase con pregunta
 */
public class Pregunta {
    private String titulo;

    /**
     * Creadora por defecto
     */
    public Pregunta() {
        titulo = "";
    }

    /**
     * Creadora de Pregunta con título
     * @param titulo Titulo de la pregunta
     */
    public Pregunta(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtener el título de la pregunta
     * @return Titulo de pregunta
     */
    public String getTitulo() {
        return titulo;

    }

    /**
     * Modificar el titulo de la pregunta
     * @param titulo El nuevo titulo de la pregunta
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtener toda la información de la pregunta a excepción del título
     * @return Contenido de la pregunta
     */
    public String getContenido () {
        return "";
    }

    /**
     * Metodo por defecto de leer
     */
    public void leer () {}

    /**
     * Obtener el tipo de la pregunta
     * @return Tipo de pregunta
     */
    public String tipo () {
        return null;
    }

    /**
     * Metodo por defecto de respuesta
     */
    public void respuesta() {}
}
