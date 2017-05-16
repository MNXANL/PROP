package com.dades;

import com.domini.Encuesta;
import com.domini.ExcFormatoIncorrecto;
import com.domini.ExcUsuarioExistente;
import com.domini.RespuestasEncuesta;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Controlador de la capa de datos
 */
public class ControladorDatos {
    String pathEnc = "src/com/dades/DirectorioEncuestas";
    String pathResp = "src/com/dades/DirectorioRespuestas";
    String pathUsers = "src/com/dades/Usuarios.txt";

    public class Tuple<X, Y> {
        public final X x;
        public final Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    HashMap<String, Tuple<String, String>> users; //nombre, pass y tipo

    public int logIn (String usuario, String pass) {
        if (users.containsKey(usuario)) {
            String p = users.get(usuario).x;
            String t = users.get(usuario).y;
            if (p.equals(pass) && t.equals("Enc")) {
                return 1;
            }
            else if (p.equals(pass) && t.equals("Admin")) {
                return 2;
            }
        }
        return 0;
    }

    public void nuevoUsuario (String tipo, String nombre, String pass) throws ExcUsuarioExistente {
        try {
            if (!users.containsKey(nombre)) {
                FileWriter fileWriter = new FileWriter(pathUsers, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                if (tipo.toUpperCase().equals("ADMIN")) {
                    bufferedWriter.write("Admin\n");
                } else if (tipo.toUpperCase().equals("ENC")) {
                    bufferedWriter.write("Enc\n");
                }
                bufferedWriter.write(nombre + "\n");
                bufferedWriter.write(pass + "\n");
                bufferedWriter.close();
                Tuple<String, String> p = new Tuple<>(pass, tipo);
                users.put(nombre, p);
            }
            else {
                System.out.println("El nombre de usuario que has introducido ya existe");
                ExcUsuarioExistente exc = new ExcUsuarioExistente("El nombre de usuario que has introducido ya existe");
                throw exc;
            }
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + pathUsers + "'");
        }
    }

    public TreeMap<String,Encuesta> cargar () throws ExcFormatoIncorrecto{
        //cargamos usuarios existentes
        users = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(pathUsers);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String tipo = null;
            while ((tipo = bufferedReader.readLine()) != null && !tipo.equals("")) {
                String nombre = bufferedReader.readLine();
                String pass = bufferedReader.readLine();
                Tuple<String, String> p = new Tuple<>(pass,tipo);
                users.put(nombre,p);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            pathUsers + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + pathUsers + "'");
        }

        //cargamos encuestas existentes
        TreeMap<String,Encuesta> enc = new TreeMap<>();

        File folder = new File(pathEnc);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            Encuesta e = Encuesta.importar(listOfFiles[i].getAbsolutePath());
            enc.put(e.getTitulo(), e);
        }

        //cargamos respuestas encuestas
        folder = new File(pathResp);
        listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
                String path = listOfFiles[i].getAbsolutePath();


            RespuestasEncuesta re = RespuestasEncuesta.importar(listOfFiles[i].getAbsolutePath());
            enc.get(re.getNombreEncuesta_respondida()).responder(re);
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

    public void guardarRespuestasEncuesta (RespuestasEncuesta re) {
        re.exportar(pathResp + "/" + re.getNombreFichero()  +".txt");
    }
    public void actualizarRespuestasEncuesta (RespuestasEncuesta re) {
        borrarRespuestasEncuesta(re.toString()); //maybe not like this
        re.exportar(pathResp + "/" + re.getNombreFichero()  +".txt");
    }

    /* Not 100% working... yet

    public void importarEncAndRespDeUnPath (String absolPath) {

        TreeMap<String,Encuesta> enc = new TreeMap<>();

        File folder = new File(absolPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            Encuesta e = null;
            try {
                e = Encuesta.importar(listOfFiles[i].getAbsolutePath());
            } catch (ExcFormatoIncorrecto excFormatoIncorrecto) {
                excFormatoIncorrecto.printStackTrace();
            }
            enc.put(e.getTitulo(), e);
        }

        //cargamos respuestas encuestas
        folder = new File(pathResp);
        listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            RespuestasEncuesta re = RespuestasEncuesta.importar(listOfFiles[i].getAbsolutePath());
            enc.get(re.getEncuesta().getTitulo()).responder(re);
        }
    }*/

    public void borrarRespuestasEncuesta (String titulo) {
        File f = new File (pathResp + "/" + titulo + ".txt");
        f.delete();
    }
}
