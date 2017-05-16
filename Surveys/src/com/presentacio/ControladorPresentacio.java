package com.presentacio;

import com.domini.ControladorDominio;
import com.domini.ExcFormatoIncorrecto;
import com.domini.ExcUsuarioExistente;

import javax.swing.*;
import java.time.Instant;
import java.util.Date;

/**
 * Controlador de la capa de presentación
 */
public class ControladorPresentacio {
    private ControladorDominio ctrlDom;

    LogIn li = null;
    VistaPrincipal vp = null;
    RegistroUsuario ru = null;
    VistaCrearEncuesta ce = null;
    private String criterio;

    public ControladorPresentacio() {
        try {
            ctrlDom = new ControladorDominio();
            criterio = "A-Z";
        }
        catch (ExcFormatoIncorrecto e) {
            //mostrar error
        }
    }

    public void inicializarPresentacion() {
        li = new LogIn(this);
        li.show();
        //Encuesta e = new Encuesta();
        //e.show();
    }

    public void logIn (String user, String pass) {
        if (ctrlDom.logIn(user,pass) == 0) {
            li.datosIncorrectos();
        }
        else {
            li.close();
            vp = new VistaPrincipal(this, ctrlDom.getUser());
            vp.llenarLista(ctrlDom.listaEncuestas("A-Z"));
            vp.show();
        }
    }

    public void logOut () {
        ctrlDom.logOut();
        li.show();
        vp.close();
    }

    public void registrarse() {
        ru = new RegistroUsuario(this);
        ru.show();
    }

    public void registrarUsuario (String user, String pass) throws ExcUsuarioExistente {
        ctrlDom.nuevoUsuario("Enc", user, pass);
        ru.close();
    }

    public void buscarEncuestas (String criterio) {
        this.criterio = criterio;
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    public void responder() {
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
            System.out.println("Importar");
        }
        else if (isel == 1) {
            ce = new VistaCrearEncuesta(this);
            ce.show();
        }
    }

    public void respuestaEncuesta() {
        //ctrlDom.responderEncuesta(...);
        ce.close();
    }

    public void buscarEncuestasPalabras (String palabras) {
        vp.llenarLista(ctrlDom.listaEncuestasPalabras(palabras));
    }

    public void buscarEncuestaFecha (String f1, String f2) {
        vp.llenarLista(ctrlDom.listaEncuestaFecha(f1,f2));
    }

    public void borrarEncuesta(String titulo) {
        //JOptionPane.showMessageDialog(vp, "Eggs are not supposed to be green.");

        ctrlDom.borrarEncuesta(titulo);
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }


}
