package com.presentacio;

import com.domini.*;

import javax.swing.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Controlador de la capa de presentación
 */
public class ControladorPresentacio {
    private ControladorDominio ctrlDom;

    LogIn li = null;
    VistaPrincipalAdmin vp = null;
    VistaPrincipalUsuario vu = null;
    RegistroUsuario ru = null;
    VistaCrearEncuesta ce = null;
    VistaRespInteractiva ri = null;
    ImportarEncuesta ie = null;
    VistaClustering vc = null;

    private String criterio;

    /**
     * Constructor del controlador de presentacion
     */
    public ControladorPresentacio() {
        try {
            ctrlDom = new ControladorDominio();
            criterio = "A-Z";
        }
        catch (ExcFormatoIncorrecto e) {
            System.out.println("error formato incorrecto");
        }
    }

    /**
     * Metodo para inicializar la vista del programa
     */
    public void inicializarPresentacion() {
        li = new LogIn(this);
        li.show();
        //Encuesta e = new Encuesta();
        //e.show();
    }

    /**
     * Metodo para iniciar sesion en el programa
     * @param user Usuario
     * @param pass Contrasenya
     */
    public void logIn (String user, String pass) {
        int codigo = ctrlDom.logIn(user, pass);
        if (codigo == 0) {
            li.datosIncorrectos();
        }
        else if (codigo == 1) {
            li.close();
            vu = new VistaPrincipalUsuario(this, ctrlDom.getUser());
            vu.llenarListaEncuestas(ctrlDom.listaEncuestasUsuario("A-Z", false));
            vu.llenarListaEncuestasRespondidas(ctrlDom.listaEncuestasUsuario("A-Z", true));
            vu.show();
        }
        else if (codigo == 2) {
            li.close();
            vp = new VistaPrincipalAdmin(this, ctrlDom.getUser());
            vp.llenarLista(ctrlDom.listaEncuestas("A-Z"));
            vp.show();
        }
    }

    /**
     * Metodo para cerrar sesion del programa
     */
    public void logOut () {
        ctrlDom.logOut();
        li.show();
        if (vp != null) {
            vp.close();
            vp = null;
            ce = null;
            ie = null;
        }
        if (vu != null) {
            vu.close();
            vu = null;
            ce = null;
            ie = null;
        }
    }

    /**
     * Metodo para mostrar la vista de registro del usuario
     */
    public void registrarse() {
        ru = new RegistroUsuario(this);
        ru.show();
    }

    /**
     * Metodo para registrar un usuario en el programa
     */
    public void registrarUsuario (String user, String pass) throws ExcUsuarioExistente {
        ctrlDom.nuevoUsuario("Enc", user, pass);
        ru.close();
    }

    /**
     * Metodo para obtener el usuario en sesion
     * @return El nombre del usuario actualmente en sesion
     */
    public String getUsername () {
        return ctrlDom.getUser();
    }

    /**
     * Metodo para buscar encuestas, segun un criterio
     * @param criterio Criterio de busqueda
     */
    public void buscarEncuestas (String criterio) {
        this.criterio = criterio;
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    /**
     * Metodo para buscar encuestas respondidas por el usuario, segun un criterio
     * @param criterio Criterio de busqueda
     */
    public void buscarEncuestasUsuario(String criterio) {
        this.criterio = criterio;
        vu.llenarListaEncuestas(ctrlDom.listaEncuestasUsuario(criterio, false));
        vu.llenarListaEncuestasRespondidas(ctrlDom.listaEncuestasUsuario(criterio, true));
    }

    /**
     * Metodo para buscar encuestas, filtradas segun palabras
     * @param palabras Palabras a filtrar
     */
    public void buscarEncuestasPalabras (String palabras) {
        vp.llenarLista(ctrlDom.listaEncuestasPalabras(palabras));
    }

    /**
     * Metodo para buscar encuestas respondidas por el usuario, filtradas segun palabras
     * @param palabras Palabras a filtrar
     */
    public void buscarEncuestasPalabrasUsuario (String palabras) {
        vu.llenarListaEncuestas(ctrlDom.listaEncuestasPalabrasUsuario(palabras, false));
        vu.llenarListaEncuestasRespondidas(ctrlDom.listaEncuestasPalabrasUsuario(palabras, true));
    }

    /**
     * Metodo para buscar encuestas, entre dos fechas
     * @param f1 Fecha de inicio
     * @param f2 Fecha final
     * @throws ParseException
     */
    public void buscarEncuestaFecha (String f1, String f2) throws ParseException{
        vp.llenarLista(ctrlDom.listaEncuestaFecha(f1,f2));
    }

    /**
     * Metodo para buscar encuestas respondidas por un usuario, entre dos fechas
     * @param f1 Fecha de inicio
     * @param f2 Fecha final
     * @throws ParseException
     */
    public void buscarEncuestaFechaUsuario (String f1, String f2) throws ParseException {
        vu.llenarListaEncuestas(ctrlDom.listaEncuestaFechaUsuario(f1,f2, false));
        vu.llenarListaEncuestasRespondidas(ctrlDom.listaEncuestaFechaUsuario(f1,f2, true));

    }

    /**
     * Metodo para crear una encuesta (importada o interactivamente)
     */
    public void crearEncuesta() {
        JOptionPane optionPane = new JOptionPane("Desea crear una encuesta interactivamente o importar una encuesta?", JOptionPane.QUESTION_MESSAGE);
        String[] opciones = {"Importar", "Crear"};
        optionPane.setOptions(opciones);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(), "AVISO");
        dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);

        String vsel = (String) optionPane.getValue();
        int isel;
        for (isel = 0; isel < opciones.length && !opciones[isel].equals(vsel); isel++) ;

        if (isel == 0) {
            ie = new ImportarEncuesta(this);
            ie.show();
        }
        else if (isel == 1) {
            ce = new VistaCrearEncuesta(this);
            ce.show();
        }
    }

    /**
     * Metodo para iniciar la visualizacion de los clusters
     * @param name Titulo de la encuesta a visualizar
     */
    public void Clusters(String name) {
        VistaKClustering vkc = new VistaKClustering(this, name, ctrlDom.getNumResps(name));
        vkc.show();
    }

    /**
     * Metodo para visualizar el clustering
     * @param name Titulo de la encuesta a visualizar
     * @param k Numero de clusters
     */
    public void Clustering(String name, int k) {
        HashMap<Integer,List<String>> c = ctrlDom.clustering(name,k);
        vc = new VistaClustering(this,c,ctrlDom.getCentroids(),name);
    }

    /**
     * Metodo para modificar una encuesta
     * @param titulo Titulo de la encuesta
     */
    public void modificarEncuesta(String titulo) {
        ce = new VistaCrearEncuesta(this, titulo, ctrlDom.getEncuestaMatrix(titulo));
        ce.show();
    }

    /**
     * Metodo para importar una encuesta de un path
     * @param path Path en el que se encuentra una encuesta
     * @throws ExcFormatoIncorrecto
     * @throws ExcEncuestaExistente
     */
    public void importarEncuesta(String path) throws ExcFormatoIncorrecto, ExcEncuestaExistente{
        ctrlDom.importarEncuesta(path);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    /**
     * Crear una encuesta pasando las preguntas al controlador de dominio
     * @param titulo Titulo de la encuesta
     * @param enc Matriz con la encuesta
     * @throws ExcEncuestaExistente
     */
    public void crearEncuestaArgs(String titulo, ArrayList<ArrayList<String>> enc) throws ExcEncuestaExistente {
        ctrlDom.crearEncuestaMatrix(titulo, enc);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    /**
     * Metodo para exportar la respuesta a un path
     * @param enc Titulo de la encuesta
     * @param path Path al que guardar la encuesta
     */
    public void exportarEncuesta(String enc, String path) {
        ctrlDom.exportarEncuesta(enc, path);
    }

    /**
     * Metodo para borrar una encuesta
     * @param titulo El titulo de la encuesta
     */
    public void borrarEncuesta(String titulo) {
        //JOptionPane.showMessageDialog(vp, "Eggs are not supposed to be green.");

        ctrlDom.borrarEncuesta(titulo);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    /**
     * Metodo para responder una encuesta
     * @param enc Titulo de la encuesta
     */
    public void responderEncuesta(String enc) {
        JOptionPane optionPane = new JOptionPane("Desea responder una encuesta interactivamente o importar las respuestas de una encuesta?", JOptionPane.QUESTION_MESSAGE);
        String[] opciones = {"Importar", "Responder"};
        optionPane.setOptions(opciones);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(), "AVISO");
        dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);

        String vsel = (String) optionPane.getValue();
        int isel;
        for (isel = 0; isel < opciones.length && !opciones[isel].equals(vsel); isel++) ;

        if (isel == 0) {
            if (vu != null)
                vu.importarRespuesta();
        }
        else if (isel == 1) {
            ri = new VistaRespInteractiva(this, enc, ctrlDom.getEncuestaMatrix(enc));
            ri.show();
        }
    }

    /**
     * Metodo para importar las respuestas de una encuesta
     * @param enc Titulo de la encuesta
     * @param path Path en el que se encuentra una encuesta
     * @throws ExcFormatoIncorrecto
     * @throws ExcUsuarioRespuestaIncorrecto
     */
    public void importarRespuestaEncuesta(String enc, String path) throws ExcFormatoIncorrecto, ExcUsuarioRespuestaIncorrecto{
        ctrlDom.importarRespuestaEncuesta(enc,path);
        //ctrlDom.verRespuestasEncuesta(enc);
    }

    /**
     * Metodo para exportar las respuestas de una encuesta
     * @param enc Titulo de la encuesta
     * @param path Path al que guardar la encuesta
     */
    public void exportarRespuestaEncuesta(String enc, String path) {
        ctrlDom.exportarRespuestaEncuesta(enc,path);
    }

    /**
     * Metodo para borrar las respuestas de una encuesta
     * @param enc Titulo de la encuesta
     */
    public void borrarRespuestaEncuesta(String enc) {
        ctrlDom.borrarRespuestaEncuesta(enc);
    }

    /**
     * Metodo para responder a una encuesta
     * @param enc Titulo de la encuesta
     * @return Si se ha podido responder la encuesta o no
     */
    public boolean encuestaRespondida(String enc) {
        if (ctrlDom.getNumResps(enc) > 0) {
            return true;
        }
        else return false;
    }

    /**
     * Metodo para actualizar la encuesta
     * @param encAnt Titulo de la encuesta a modificar
     * @param enc Titulo a actualizar de la encuesta
     * @param preguntasGuardadas Matriz de preguntas guardadas
     * @throws ExcEncuestaExistente
     */
    public void actualizarEncuestaArgs(String encAnt, String enc, ArrayList<ArrayList<String>> preguntasGuardadas) throws ExcEncuestaExistente {
        ctrlDom.actualizarEncuestaMatrix(encAnt, enc, preguntasGuardadas);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    /**
     * Metodo para guardar las respuestas de una encuesta
     * @param titulo Titulo de la encuesta
     * @param resps Matriz de respuestas a la encuesta
     */
    public void guardarRespEnc(String titulo, ArrayList<ArrayList<String>> resps) {
        ctrlDom.responderEncuestaMatrix(titulo, resps);
        vu.buscar();
    }
}
