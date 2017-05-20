package com.domini;

//import com.sun.tools.javac.util.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

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

    public void addEncuesta (Encuesta e) throws ExcEncuestaExistente{
        if (!encuestas.containsKey(e.getTitulo())) {
            encuestas.put(e.getTitulo(), e);
        }
        else {
            ExcEncuestaExistente exc = new ExcEncuestaExistente("La encuesta con título " + e.getTitulo() + " ya existe.");
            throw exc;
        }
    }

    public void borrarEncuesta (String titulo) {
        encuestas.remove(titulo);
    }

    public void borrarRespuesta(String titulo, String user) {
        encuestas.get(titulo).borrarRespuesta(user);
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
        else if (criterio.equals("Nuevas")) {
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
        else if (criterio.equals("Antiguas")) {
            String[] titulos = encuestas.keySet().toArray(new String[encuestas.keySet().size()]);
            Arrays.sort(titulos, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (encuestas.get(o2).getFecha().before(encuestas.get(o1).getFecha())) {
                        return 1;
                    } else if (encuestas.get(o1).getFecha().before(encuestas.get(o2).getFecha())) {
                        return -1;
                    }
                    return 0;
                }
            });
            return titulos;
        }
        return null;
    }

    public String[] getTitulosEncuestasUsuario (String criterio, String user, boolean respondidas) {
        if (criterio.equals("A-Z")) {
            Set<String> enc = new TreeSet<>(encuestas.keySet());
            Iterator<String> iterator = enc.iterator();
            while (iterator.hasNext()) {
                if (respondidas) {
                    if (!encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
                else {
                    if (encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
            }
            return enc.toArray(new String[enc.size()]);
        }
        else if (criterio.equals("Z-A")) {
            Set<String> enc = new TreeSet<>(encuestas.keySet());
            Iterator<String> iterator = enc.iterator();
            while (iterator.hasNext()) {
                if (respondidas) {
                    if (!encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
                else {
                    if (encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
            }
            String[] titulos = enc.toArray(new String[enc.size()]);
            int n = titulos.length;
            for (int i = 0; i < n/2; i++) {
                String temp = titulos[i];
                titulos[i] = titulos[n-1-i];
                titulos[n-1-i] = temp;
            }
            return titulos;
        }
        else if (criterio.equals("Nuevas")) {
            Set<String> enc = new TreeSet<>(encuestas.keySet());
            Iterator<String> iterator = enc.iterator();
            while (iterator.hasNext()) {
                if (respondidas) {
                    if (!encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
                else {
                    if (encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
            }
            String[] titulos = enc.toArray(new String[enc.size()]);
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
        else if (criterio.equals("Antiguas")) {
            Set<String> enc = new TreeSet<>(encuestas.keySet());
            Iterator<String> iterator = enc.iterator();
            while (iterator.hasNext()) {
                if (respondidas) {
                    if (!encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
                else {
                    if (encuestas.get(iterator.next()).respondida(user)) iterator.remove();
                }
            }
            String[] titulos = enc.toArray(new String[enc.size()]);
            Arrays.sort(titulos, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (encuestas.get(o2).getFecha().before(encuestas.get(o1).getFecha())) {
                        return 1;
                    } else if (encuestas.get(o1).getFecha().before(encuestas.get(o2).getFecha())) {
                        return -1;
                    }
                    return 0;
                }
            });
            return titulos;
        }
        return null;
    }


    /**
     * Devuelve los títulos de las encuestas que se encuentren en el intervalo indicado con fecha1 y fecha2 (inclusivo)
     * @param fecha1 fecha más antigua a partir de la cual buscar
     * @param fecha2 fecha más reciente
     * @return
     */
    public String[] getTitulosEncuestasFecha (Date fecha1, Date fecha2) {
        String[] t = encuestas.keySet().toArray(new String[encuestas.keySet().size()]);
        ArrayList<String> titulos = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            if (encuestas.get(t[i]).getFecha().after(fecha1) && encuestas.get(t[i]).getFecha().before(fecha2)) {
                titulos.add(t[i]);
            }
        }
        return titulos.toArray(new String[titulos.size()]);
    }

    public String[] getTitulosEncuestasFechaUsuario (Date fecha1, Date fecha2, String user, boolean respondidas) {
        Set<String> enc = new TreeSet<>(encuestas.keySet());
        Iterator<String> iterator = enc.iterator();
        while (iterator.hasNext()) {
            if (respondidas) {
                if (!encuestas.get(iterator.next()).respondida(user)) iterator.remove();
            }
            else {
                if (encuestas.get(iterator.next()).respondida(user)) iterator.remove();
            }
        }
        String[] t = enc.toArray(new String[enc.size()]);
        ArrayList<String> titulos = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            if (encuestas.get(t[i]).getFecha().after(fecha1) && encuestas.get(t[i]).getFecha().before(fecha2)) {
                titulos.add(t[i]);
            }
        }
        return titulos.toArray(new String[titulos.size()]);
    }


    public String[] getTitulosEncuestasPalabras (String palabras) {
        String[] t = encuestas.keySet().toArray(new String[encuestas.keySet().size()]);
        ArrayList<String> titulos = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            if (t[i].contains(palabras)) {
                titulos.add(t[i]);
            }
        }
        return titulos.toArray(new String[titulos.size()]);
    }

    public String[] getTitulosEncuestasPalabrasUsuario (String palabras, String user, boolean respondidas) {
        Set<String> enc = new TreeSet<>(encuestas.keySet());
        Iterator<String> iterator = enc.iterator();
        while (iterator.hasNext()) {
            if (respondidas) {
                if (!encuestas.get(iterator.next()).respondida(user)) iterator.remove();
            }
            else {
                if (encuestas.get(iterator.next()).respondida(user)) iterator.remove();
            }
        }
        String[] t = enc.toArray(new String[enc.size()]);
        ArrayList<String> titulos = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            if (t[i].contains(palabras)) {
                titulos.add(t[i]);
            }
        }
        return titulos.toArray(new String[titulos.size()]);
    }

    public Encuesta getEncuesta (String titulo) {
        return encuestas.get(titulo);
    }

    public void cambiarTitulo (String nuevoTitulo, String viejoTitulo) {
        encuestas.put(nuevoTitulo, encuestas.remove(viejoTitulo));
    }

}
