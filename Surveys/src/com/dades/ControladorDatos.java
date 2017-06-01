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

    String os;

    /**
     * Clase para representar una tupla
     * @param <X>
     * @param <Y>
     */
    public class Tuple<X, Y> {
        public final X x;
        public final Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    HashMap<String, Tuple<String, String>> users; //nombre, pass y tipo

    /**
     * Connstructor por defecto de la capa de datos, adapta los path para SO Windows
     */
    public ControladorDatos() {
        os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            pathEnc = "src\\com\\dades\\DirectorioEncuestas";
            pathResp = "src\\com\\dades\\DirectorioRespuestas";
            pathUsers = "src\\com\\dades\\Usuarios.txt";
        }
    }

    /**
     * Método para hacer login del programa
     * @param usuario Usuario que entra
     * @param pass Contraseña del usuario
     * @return Codigo de login: 0 si no existe usuario, 1 con contraseña incorrecta y 2 login correcto
     */
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

    /**
     * Crear nuevo usuario
     * @param tipo Privilegios de usuario
     * @param nombre Nombre del usuario
     * @param pass Contraseña del usuario
     * @throws ExcUsuarioExistente
     */
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

    /**
     * Cargar los datos de disco al programa
     * @return Arbol que representa un conjunto de encuestas
     * @throws ExcFormatoIncorrecto
     */
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
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + pathUsers + "'");
        } catch(IOException ex) {
            System.out.println("Error reading file '" + pathUsers + "'");
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

    /**
     * Metodo para guardar encuesta en disco
     * @param e Encuesta a guardar
     */
    public void guardarEncuesta (Encuesta e) {
        if (os.indexOf("win") >= 0) {
            e.exportar(pathEnc + "\\" + e.getTitulo() + ".txt");
        } else {
            e.exportar(pathEnc + "/" + e.getTitulo() + ".txt");
        }
    }

    /**
     * Metodo para actualizar encuesta en disco
     * @param titulo Titulo de la encuesta a actualizar
     * @param e Encuesta a actualizar
     */
    public void actualizarEncuesta (String titulo, Encuesta e) {
        borrarEncuesta(titulo);
        if (os.indexOf("win") >= 0) {
            e.exportar(pathEnc+"\\"+e.getTitulo()+".txt");
        }
        else {
            e.exportar(pathEnc + "/" + e.getTitulo() + ".txt");
        }
    }

    /**
     * Metodo para borrar encuesta en disco
     * @param titulo Titulo de la encuesta a borrar
     */
    public void borrarEncuesta (String titulo) {
        File f;
        if (os.indexOf("win") >= 0) {
            f = new File (pathEnc+"\\"+titulo+".txt");
        } else {
            f = new File(pathEnc + "/" + titulo + ".txt");
        }
        f.delete();
    }

    /**
     * Metodo para guardar respuestas de encuesta en disco
     * @param re Respuestas de encuesta a guardar
     */
    public void guardarRespuestasEncuesta (RespuestasEncuesta re) {
        if (os.indexOf("win") >= 0) {
            re.exportar(pathResp + "\\" + re.getNombreFichero()  +".txt");
        }
        else {
            re.exportar(pathResp + "/" + re.getNombreFichero() + ".txt");
        }
    }

    /**
     * Metodo para actualizar respuestas de encuesta en disco
     * @param re Respuestas de encuesta a actualizar
     */
    public void actualizarRespuestasEncuesta (RespuestasEncuesta re) {
        borrarRespuestasEncuesta(re.toString());
        if (os.indexOf("win") >= 0) {
            re.exportar(pathResp + "/" + re.getNombreFichero()  +".txt");
        }
        else {
            re.exportar(pathResp + "/" + re.getNombreFichero() + ".txt");
        }
    }

    /**
     * Metodo para borrar respuestas de encuesta en disco
     * @param titulo Respuestas de encuesta a borrar
     */
    public void borrarRespuestasEncuesta (String titulo) {
        File f;
        if (os.indexOf("win") >= 0) {
            f = new File (pathResp + "/" + titulo + ".txt");
        }
        else {
            f = new File(pathResp + "/" + titulo + ".txt");
        }
        f.delete();
    }
}
