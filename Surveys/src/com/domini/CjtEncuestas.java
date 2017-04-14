package com.domini;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by aleixballetbo on 13/4/17.
 */
public class CjtEncuestas {
    TreeMap<String, Encuesta> encuestas;

    public CjtEncuestas () {
        encuestas = new TreeMap<String, Encuesta>();
    }

    public CjtEncuestas (TreeMap<String,Encuesta> enc) {
        encuestas = enc;
    }

    public void addEncuesta (Encuesta e) {
        encuestas.put(e.getTitulo(), e);
    }

    public void borrarEncuesta (String titulo) {
        encuestas.remove(titulo);
    }

    public String[] getTitulosEncuestas (String criterio) {
        if (criterio.equals("A-Z")) {
            return encuestas.keySet().toArray(new String[encuestas.keySet().size()]);
        }
        else if (criterio.equals("Z-A")) {
            String[] titulos = encuestas.keySet().toArray(new String[encuestas.keySet().size()]);
            int n = titulos.length;
            for (int i = 0; i < n/2; i++) {
                String temp = titulos[i];
                titulos[i] = titulos[n-1-i];
                titulos[n-1-i] = temp;
            }
            return titulos;
        }
        else if (criterio.equals("nuevas")) {
            String[] titulos = encuestas.keySet().toArray(new String[encuestas.keySet().size()]);
            Arrays.sort(titulos, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (encuestas.get(o1).getFecha().before(encuestas.get(o2).getFecha())) {
                        return 1;
                    }
                    else if (encuestas.get(o2).getFecha().before(encuestas.get(o1).getFecha())) {
                        return -1;
                    }
                    return 0;
                }
            });
            return titulos;
        }
        else if (criterio.equals("antiguas")) {
            String[] titulos = encuestas.keySet().toArray(new String[encuestas.keySet().size()]);
            Arrays.sort(titulos, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (encuestas.get(o2).getFecha().before(encuestas.get(o1).getFecha())) {
                        return 1;
                    }
                    else if (encuestas.get(o1).getFecha().before(encuestas.get(o2).getFecha())) {
                        return -1;
                    }
                    return 0;
                }
            });
            return titulos;
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
