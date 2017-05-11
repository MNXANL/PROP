package com.presentacio;

import com.domini.ControladorDominio;
import com.domini.ExcFormatoIncorrecto;

/**
 * Controlador de la capa de presentación
 */
public class ControladorPresentacio {
    private ControladorDominio ctrlDom;

    LogIn li = null;

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
            //inicializar aplicacion
        }
    }
}
