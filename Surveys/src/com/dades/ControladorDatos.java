package com.dades;

import com.domini.Encuesta;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Controlador de la capa de datos
 */
public class ControladorDatos {
    String pathEnc = "src/com/dades/DirectorioEncuestas";

    public TreeMap<String,Encuesta> cargar () {
        TreeMap<String,Encuesta> enc = new TreeMap<>();

        File folder = new File(pathEnc);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            Encuesta e = Encuesta.importar(listOfFiles[i].getAbsolutePath());
            enc.put(e.getTitulo(), e);
        }

        return enc;
    }

    public void guardarEncuesta (Encuesta e) {
        e.exportar(pathEnc+"/"+e.getTitulo()+".txt");
    }

    public void actualizarEncuesta (String titulo, Encuesta e) {
        borrarEncuesta(titulo);
        e.exportar(pathEnc+"/"+e.getTitulo()+".txt");
    }

    public void borrarEncuesta (String titulo) {
        File f = new File (pathEnc+"/"+titulo+".txt");
        f.delete();
    }
}
