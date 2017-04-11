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
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void leer ()     {}

    public void respuesta() {}
}
