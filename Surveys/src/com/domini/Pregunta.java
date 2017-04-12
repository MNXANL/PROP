package com.domini;

/**
 * Clase con pregunta
 */
public class Pregunta {
    private String titulo;

    public Pregunta() {
        titulo = "";
    }

    public Pregunta(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;

    }

    public String getContenido () {
        return "";
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void leer ()     {}

    public String tipo () {
        return null;
    }

    public void respuesta() {}
}
