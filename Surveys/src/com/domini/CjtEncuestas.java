package com.domini;

import java.util.HashMap;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by aleixballetbo on 13/4/17.
 */
public class CjtEncuestas {
    HashMap<String, Encuesta> encuestas;

    public CjtEncuestas () {
        encuestas = new HashMap<String, Encuesta>();
    }

    public void addEncuesta (Encuesta e) {
        encuestas.put(e.getTitulo(), e);
    }

    public Set<String> getTitulosEncuestas () {
        return encuestas.keySet();
    }

    public Encuesta getEncuesta (String titulo) {
        return encuestas.get(titulo);
    }

    public void cambiarTitulo (String nuevoTitulo, String viejoTitulo) {
        encuestas.put(nuevoTitulo, encuestas.remove(viejoTitulo));
    }
}
