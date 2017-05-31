package com.domini;

import com.dades.ControladorDatos;
import com.sun.corba.se.impl.logging.InterceptorsSystemException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Controlador de la capa de dominio
 */
public class ControladorDominio {
    private Encuesta e;
    private Usuario u;
    private Clustering cl;

    ControladorDatos contDatos;

    CjtEncuestas cjt;

    /**
     * Creadora de ControladorDominio, se encarga de inicializar el programa
     * @throws ExcFormatoIncorrecto Se activa cuando una encuesta guardada no tiene el formato correcto
     */
    public ControladorDominio() throws ExcFormatoIncorrecto{
        contDatos = new ControladorDatos();
        cjt = new CjtEncuestas(contDatos.cargar());
        u = null;
    }

    /**
     * Operación que permite identificarse en el sistema
     * @param usuario usuario que quiere identificarse
     * @param pass contrasenya del usuario
     * @return devuelve 0 si los datos no son correctos, 1 si los datos son correctos y es un usuario encuestado y 2 si los datos son correctos y es un usuario administrador
     */
    public int logIn (String usuario, String pass) {
        int x = contDatos.logIn(usuario,pass);
        switch (x) {
            case 0:
                break;
            case 1:
                u = new Encuestado(usuario);
                break;
            case 2:
                u = new Administrador(usuario);
                break;
        }
        return x;
    }

    /**
     * Consultora del usuario identificado en el sistema en un momento concreto
     * @return Nombre del usuario identificado
     */
    public String getUser() {
        return u.getUsername();
    }

    /**
     * Operación para cerrar sesion en el sistema
     */
    public void logOut () {
        u = null;
    }

    /**
     * Operación para crear un nuevo usuario en el sistema
     * @param tipo Encuestado o Administrador
     * @param nombre Nombre del usuario
     * @param pass Contraseña del usuario
     * @throws ExcUsuarioExistente Se activa cuando ya existe otro usuario registrado en el sistema con el mismo nombre
     */
    public void nuevoUsuario (String tipo, String nombre, String pass) throws ExcUsuarioExistente {
        contDatos.nuevoUsuario (tipo, nombre, pass);
    }

    /**
     *
     * @param name
     * @param k
     * @return
     */
    public HashMap<Integer,List<String>> clustering(String name,int k){
        Encuesta x = cjt.getEncuesta(name);
        cl = new Clustering(x,k);
        return cl.run();
    }

    /**
     * HACER SIEMPRE LA FUNCION CLUSTERING ANTES
     * @return
     */
    public ArrayList<RespuestasEncuesta> getCentroids(){
        return cl.getCentroids();
    }

    /**
     * Metodo para crear la encuesta desde la vista creadora
     * @param enc Matriz de encuesta con sus preguntas (una por indice, salvo el indice 0 que guarda el título)
     * @throws ExcEncuestaExistente Se activa si ya existe otra encuesta con el mismo título en el sistema
     */
    public void crearEncuestaMatrix(String titulo, ArrayList<ArrayList<String>> enc) throws ExcEncuestaExistente {
        e = new Encuesta(titulo);
        for (int i = 0; i < enc.size(); i++) {
            Pregunta p = null;
            if (enc.get(i).get(0).equals("PN")) {
                p = new PregNumerica(enc.get(i).get(1), Double.parseDouble(enc.get(i).get(2)), Double.parseDouble(enc.get(i).get(3)));
            }
            else if (enc.get(i).get(0).equals("PRL")) {
                p = new PregRespuestaLibre(enc.get(i).get(1));
            }
            else if (enc.get(i).get(0).equals("PCO")) {
                ArrayList<String> opts = new ArrayList<>();
                for (int j = 2; j != enc.get(i).size(); ++j) {
                    opts.add(enc.get(i).get(j));
                }
                p = new PregCualitativaOrdenada(enc.get(i).get(1), opts);
            }
            else if (enc.get(i).get(0).equals("PCNOU")) {
                ArrayList<String> opts = new ArrayList<>();
                for (int j = 2; j != enc.get(i).size(); ++j) {
                    opts.add(enc.get(i).get(j));
                }
                p = new PregCualitativaNoOrdenadaUnica(enc.get(i).get(1), opts);

            }
            else if (enc.get(i).get(0).equals("PCNOM")) {
                int maxOpts = Integer.parseInt(enc.get(i).get(2));
                ArrayList<String> opts = new ArrayList<>();
                for (int j = 3; j != enc.get(i).size(); ++j) {
                    opts.add(enc.get(i).get(j));
                }
                p = new PregCualitativaNoOrdenadaMultiple( enc.get(i).get(1), opts, maxOpts );
            }
            e.add_question(p);
            System.out.println(" --> Pregunta" + i + " OK");
        }
        System.out.println("Encuesta " + e.getTitulo() + " guardada con éxito");
        cjt.addEncuesta(e);
        contDatos.guardarEncuesta(e);
        e.leer();
    }

    /**
     * Consultora del contenido de una encuesta
     * @param tituloE Titulo de la encuesta a consultar
     * @return Datos de la encuesta en formato de matriz de Strings
     */
    public ArrayList<ArrayList<String>> getEncuestaMatrix (String tituloE) {
        return cjt.getEncuesta(tituloE).getMatrix();
    }

    /**
     * Operacion para importar una encuesta externa al sistema
     * @param path Localización de la encuesta en el sistema de ficheros
     * @throws ExcFormatoIncorrecto Se activa cuando el archivo que contiene la encuesta tiene errores de formato
     * @throws ExcEncuestaExistente Se activa cuando ya existe otra encuesta con el mismo nombre que la que se quiere importar
     */
    public void importarEncuesta (String path) throws ExcFormatoIncorrecto, ExcEncuestaExistente{
        Encuesta e  = Encuesta.importar(path);
        cjt.addEncuesta(e);
        contDatos.guardarEncuesta(e);
    }

    /**
     * Operacion para exportar una encuesta del sistema a un archivo externo
     * @param enc Titulo de la encuesta a exportar
     * @param path Localización donde se exportara el archivo
     */
    public void exportarEncuesta (String enc, String path) {
        cjt.getEncuesta(enc).exportar(path);
    }

    /**
     * Operacion para obtener la lista de encuestas para la vista de administrador ordenada según un criterio
     * @param criterio Criterio de ordenación de encuestas (A-Z, Z-A, Nuevas, Antiguas)
     * @return Lista de los nombres de encuesta ordenada segun el criterio
     */
    public String[] listaEncuestas (String criterio) {
        return cjt.getTitulosEncuestas(criterio);
    }

    /**
     * Operacion para obtener la lista de encuestas para la vista de usuario ordenada según un criterio
     * @param criterio Criterio de ordenación de encuestas (A-Z, Z-A, Nuevas, Antiguas)
     * @param respondidas Booleano que inidica si se quieren obtener las encuestas respondidas o las no respondidas
     * @return Lista de los nombres de encuesta ordenada segun el criterio
     */
    public String[] listaEncuestasUsuario (String criterio, boolean respondidas) {
        return cjt.getTitulosEncuestasUsuario(criterio, this.getUser(), respondidas);
    }

    /**
     * Operacion para obtener la lista de encuestas para la vista de administrador cuyos titulos coinciden con una o varias palabras
     * @param palabras Palabras con las que se comparan los titulos de las encuestas
     * @return Lista de los nombres de las encuestas que contienen las palabras introducidas
     */
    public String[] listaEncuestasPalabras (String palabras) {
        return  cjt.getTitulosEncuestasPalabras(palabras);
    }

    /**
     * Operacion para obtener la lista de encuestas para la vista de administrador cuyos titulos coinciden con una o varias palabras
     * @param palabras Palabras con las que se comparan los titulos de las encuestas
     * @param respondidas Booleano que inidica si se quieren obtener las encuestas respondidas o las no respondidas
     * @return Lista de los nombres de las encuestas que contienen las palabras introducidas
     */
    public String[] listaEncuestasPalabrasUsuario (String palabras, boolean respondidas) {
        return  cjt.getTitulosEncuestasPalabrasUsuario(palabras, this.getUser(), respondidas);
    }

    /**
     * Opearacion para obtener la lista de encuestas para la vista de administrador que fueron creadas entre dos fechas (inclusivas)
     * @param f1 Fecha de inicio del intervalo
     * @param f2 Fecha de fin del intervalo
     * @return Lista de los nombres de las encuestas que fueron creadas en dicho intervalo
     * @throws ParseException Se activa cuando el formato de las fechas no es el correcto
     */
    public String[] listaEncuestaFecha (String f1, String f2) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date fecha1 = sdf.parse(f1);
        Date fecha2 = sdf.parse(f2);
        fecha2.setHours(23);
        fecha2.setMinutes(59);
        fecha2.setSeconds(59);
        return cjt.getTitulosEncuestasFecha(fecha1, fecha2);
    }

    /**
     * Opearacion para obtener la lista de encuestas para la vista de usuario que fueron creadas entre dos fechas (inclusivas)
     * @param f1 Fecha de inicio del intervalo
     * @param f2 Fecha de fin del intervalo
     * @param respondidas Booleano que inidica si se quieren obtener las encuestas respondidas o las no respondidas
     * @return Lista de los nombres de las encuestas que fueron creadas en dicho intervalo
     * @throws ParseException Se activa cuando el formato de las fechas no es el correcto
     */
    public String[] listaEncuestaFechaUsuario (String f1, String f2, boolean respondidas) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date fecha1 = sdf.parse(f1);
        Date fecha2 = sdf.parse(f2);
        fecha2.setHours(23);
        fecha2.setMinutes(59);
        fecha2.setSeconds(59);
        return cjt.getTitulosEncuestasFechaUsuario(fecha1, fecha2, this.getUser(), respondidas);
    }

    /**
     * Operacion para borrar una de las encuestas del sistema
     * @param titulo Titulo de la encuesta a borrar
     */
    public void borrarEncuesta (String titulo) {
        cjt.borrarEncuesta(titulo);
        contDatos.borrarEncuesta(titulo);
    }

    public void responderEncuestaMatrix(String titulo, ArrayList<ArrayList<String>> resps) {
        e = cjt.getEncuesta(titulo);
        ArrayList<Respuesta> ALR = new ArrayList<>();
        for (int i = 0; i != resps.size(); ++i) {
            Respuesta r = null;
            ArrayList<String> r_ith = resps.get(i);
            if (r_ith.get(0).equals("RN")) {
                r = new RespNumerica(Double.parseDouble(r_ith.get(1)), Double.parseDouble(r_ith.get(2)), Double.parseDouble(r_ith.get(3)));
            }
            else if (r_ith.get(0).equals("RL")) {
                r = new RespLibre(r_ith.get(1));

            }
            else if (r_ith.get(0).equals("RCO")) {
                r = new RespCualitativaOrdenada(Integer.parseInt(r_ith.get(1)), Integer.parseInt(r_ith.get(2)), r_ith.get(3));
            }
            else if (r_ith.get(0).equals("RCNOU")) {
                r = new RespCualitativaNoOrdenadaUnica(Integer.parseInt(r_ith.get(1)), r_ith.get(2));
            }
            else if (r_ith.get(0).equals("RCNOM")) {

                HashMap<Integer, String> str = new HashMap<>();
                for (int j = 1; j < r_ith.size(); j += 2) {
                    str.put( Integer.parseInt(r_ith.get(j)), r_ith.get(j + 1) );
                }
                r = new RespCualitativaNoOrdenadaMultiple(str);
            }
            ALR.add(r);
        }
        RespuestasEncuesta re = new RespuestasEncuesta(e, u.getUsername(), ALR);

        e.responder(re);
        System.out.println("Guardando encuesta...");
        contDatos.guardarRespuestasEncuesta(re);
        System.out.println("Guardada.");
    }

    public void importarRespuestaEncuesta(String tituloE, String path) throws ExcFormatoIncorrecto, ExcUsuarioRespuestaIncorrecto {
        RespuestasEncuesta re = RespuestasEncuesta.importar(path);
        if (!re.getUser().equals(this.getUser()) && !this.getUser().equals("admin")) {
            ExcUsuarioRespuestaIncorrecto exc = new ExcUsuarioRespuestaIncorrecto("El usuario que figura en el documento de respuesta no se corresponde con el usuario actual del sistema");
            throw exc;
        }
        if (re.getNombreEncuesta_respondida().equals(tituloE)) {
            boolean respuestaExistente = cjt.getEncuesta(tituloE).responder(re);
            if (respuestaExistente) {
                contDatos.actualizarRespuestasEncuesta(re);
            } else {
                contDatos.guardarRespuestasEncuesta(re);
            }
        }
        else {
            //exc;
        }
    }

    public void exportarRespuestaEncuesta(String tituloE, String path) {
        cjt.getEncuesta(tituloE).getRespuesta(u.getUsername()).exportar(path);
    }

    public void borrarRespuestaEncuesta (String titulo) {
        cjt.borrarRespuesta(titulo, u.getUsername());
        contDatos.borrarRespuestasEncuesta(titulo + "_" + u.getUsername());
    }

    public int getNumResps(String enc) {
        return cjt.getEncuesta(enc).getNumResps();
    }

    public void actualizarEncuestaMatrix(String tituloAnt, String titulo, ArrayList<ArrayList<String>> preguntasGuardadas) throws ExcEncuestaExistente {
        cjt.borrarEncuesta(tituloAnt);
        crearEncuestaMatrix(titulo, preguntasGuardadas);
        contDatos.actualizarEncuesta(tituloAnt, cjt.getEncuesta(titulo));
    }
}
