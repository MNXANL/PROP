package com.presentacio;

import com.domini.ControladorDominio;
import com.domini.ExcFormatoIncorrecto;

import java.time.Instant;
import java.util.Date;

/**
 * Controlador de la capa de presentación
 */
public class ControladorPresentacio {
    private ControladorDominio ctrlDom;

    LogIn li = null;
    VistaPrincipal vp = null;

    public ControladorPresentacio() {
        try {
            ctrlDom = new ControladorDominio();
        }
        catch (ExcFormatoIncorrecto e) {
            //mostrar error
        }
    }

    public void inicializarPresentacion() {
        li = new LogIn(this);
        li.show();
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

    public void buscarEncuestas (String criterio) {
        vp.llenarLista(ctrlDom.listaEncuestas(criterio));
    }

    public void buscarEncuestasPalabras (String palabras) {
        vp.llenarLista(ctrlDom.listaEncuestasPalabras(palabras));
    }

    public void buscarEncuestaFecha (String f1, String f2) {
        vp.llenarLista(ctrlDom.listaEncuestaFecha(f1,f2));
    }
}
