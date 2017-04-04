package com.prop;

/**
 * Created by mike on 3/28/17.
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

    }public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
