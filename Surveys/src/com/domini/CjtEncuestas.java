package com.domini;

import java.util.Collections;
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

    public CjtEncuestas (HashMap<String,Encuesta> enc) {
        encuestas = enc;
    }

    public void addEncuesta (Encuesta e) {
        encuestas.put(e.getTitulo(), e);
    }

    public void borrarEncuesta (String titulo) {
        encuestas.remove(titulo);
    }

    public Set<String> getTitulosEncuestas (String criterio) {
        if (criterio.equals("A-Z")) {
            //treeMap
            return encuestas.keySet();
        }
        else if (criterio.equals("Z-A")) {

        }
        else if (criterio.equals("nuevas")) {

        }
        else if (criterio.equals("antiguas")) {

        }
        return null;
    }

    public Encuesta getEncuesta (String titulo) {
        return encuestas.get(titulo);
    }

    public void cambiarTitulo (String nuevoTitulo, String viejoTitulo) {
        encuestas.put(nuevoTitulo, encuestas.remove(viejoTitulo));
    }
}
