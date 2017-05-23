package com.presentacio;

import com.domini.*;

import javax.swing.*;
import java.util.ArrayList;

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

    private String criterio;

    public ControladorPresentacio() {
        try {
            ctrlDom = new ControladorDominio();
            criterio = "A-Z";
        }
        catch (ExcFormatoIncorrecto e) {
            System.out.println("error formato incorrecto");
        }
    }

    public void inicializarPresentacion() {
        li = new LogIn(this);
        li.show();
        //Encuesta e = new Encuesta();
        //e.show();
    }

    public void logIn (String user, String pass) {
        int codigo = ctrlDom.logIn(user,pass);
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

    public void registrarse() {
        ru = new RegistroUsuario(this);
        ru.show();
    }

    public void registrarUsuario (String user, String pass) throws ExcUsuarioExistente {
        ctrlDom.nuevoUsuario("Enc", user, pass);
        ru.close();
    }

    public String getUsername () {
        return ctrlDom.getUser();
    }

    public void buscarEncuestas (String criterio) {
        this.criterio = criterio;
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    public void buscarEncuestasUsuario(String criterio) {
        this.criterio = criterio;
        vu.llenarListaEncuestas(ctrlDom.listaEncuestasUsuario(criterio, false));
        vu.llenarListaEncuestasRespondidas(ctrlDom.listaEncuestasUsuario(criterio, true));
    }

    public void buscarEncuestasPalabras (String palabras) {
        vp.llenarLista(ctrlDom.listaEncuestasPalabras(palabras));
    }

    public void buscarEncuestasPalabrasUsuario (String palabras) {
        vu.llenarListaEncuestas(ctrlDom.listaEncuestasPalabrasUsuario(palabras, false));
        vu.llenarListaEncuestasRespondidas(ctrlDom.listaEncuestasPalabrasUsuario(palabras, true));
    }

    public void buscarEncuestaFecha (String f1, String f2) {
        vp.llenarLista(ctrlDom.listaEncuestaFecha(f1,f2));
    }

    public void buscarEncuestaFechaUsuario (String f1, String f2) {
        vu.llenarListaEncuestas(ctrlDom.listaEncuestaFechaUsuario(f1,f2, false));
        vu.llenarListaEncuestasRespondidas(ctrlDom.listaEncuestaFechaUsuario(f1,f2, true));

    }

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

    public void modificarEncuesta(String titulo) {
        ce = new VistaCrearEncuesta(this, titulo, ctrlDom.getEncuestaMatrix(titulo));
        ce.show();
    }

    public void importarEncuesta(String path) throws ExcFormatoIncorrecto, ExcEncuestaExistente{
        ctrlDom.importarEncuesta(path);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    public void crearEncuestaArgs(String titulo, ArrayList<ArrayList<String>> enc) throws ExcEncuestaExistente {
        ctrlDom.crearEncuestaMatrix(titulo, enc);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    public void exportarEncuesta(String enc, String path) {
        ctrlDom.exportarEncuesta(enc, path);
    }

    public void borrarEncuesta(String titulo) {
        //JOptionPane.showMessageDialog(vp, "Eggs are not supposed to be green.");

        ctrlDom.borrarEncuesta(titulo);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    public void responderEncuesta() {
        JOptionPane optionPane = new JOptionPane("Desea responder una encuesta interactivamente o importar las respuestas de una encuesta?", JOptionPane.QUESTION_MESSAGE);
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
            if (vp != null)
                vp.importarRespuesta();
            else if (vu != null)
                vu.importarRespuesta();
        }
        else if (isel == 1) {
            ri = new VistaRespInteractiva(this, null);
            ri.show();
        }
    }

    public void importarRespuestaEncuesta(String enc, String path) throws ExcFormatoIncorrecto, ExcUsuarioRespuestaIncorrecto{
        ctrlDom.importarRespuestaEncuesta(enc,path);
        //ctrlDom.verRespuestasEncuesta(enc);
    }

    public void exportarRespuestaEncuesta(String enc, String path) {
        ctrlDom.exportarRespuestaEncuesta(enc,path);
    }

    public void borrarRespuestaEncuesta(String enc) {
        ctrlDom.borrarRespuestaEncuesta(enc);
    }

    public void respuestaEncuesta() {
        //ctrlDom.responderEncuesta(...);
        ce.close();
    }


    public void actualizarEncuestaArgs(String text, ArrayList<ArrayList<String>> preguntasGuardadas) {
        ctrlDom.actualizarEncuestaMatrix(text, preguntasGuardadas);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }
}
